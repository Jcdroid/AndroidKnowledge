package com.jcdroid.androidknowledge.utils;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Android Apk更新器，可执行apk下载和安装
 * <br/>
 * Created by Jcdroid on 2018/7/11.
 */
public class UpdateManger {

    private Context mContext;

    private String checkUpdateUrl;

    private long downloadId = 0;

    private MyHandler mHandler = new MyHandler(this);

    private static class MyHandler extends Handler {

        private WeakReference<UpdateManger> updateMangerWeakReference;

        public MyHandler(UpdateManger context) {
            super();
            updateMangerWeakReference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            UpdateManger updateManger = updateMangerWeakReference.get();
            if (msg != null) {
                UpdateInfo updateInfo = (UpdateInfo) msg.obj;
                updateManger.showUpdateDialog(updateInfo);
            }
        }
    }

    private UpdateManger(Builder builder) {
        super();
        this.mContext = builder.mContext;
        this.checkUpdateUrl = builder.checkUpdateUrl;
    }

    public void checkUpdate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(checkUpdateUrl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.connect();

                    int code = httpURLConnection.getResponseCode();
                    if (code == 200) {
                        String result = "";
                        InputStream in = httpURLConnection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            result += line;
                        }
                        in.close();
                        reader.close();
                        Message msg = mHandler.obtainMessage();
                        msg.obj = new Gson().fromJson(result, UpdateInfo.class);
                        mHandler.sendMessage(msg);
                    }
                    httpURLConnection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showUpdateDialog(final UpdateInfo updateInfo) {
        if (needShowUpdateDialog(updateInfo)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle(String.format("有新版本%s可以更新", updateInfo.versionName));
            builder.setMessage(updateInfo.updateMessage);
            builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    downloadApk(updateInfo);
                }
            });
            if (updateInfo.mustUpdate) {
                builder.setCancelable(false);
            } else {
                builder.setNegativeButton("跳过", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
            }
            builder.show();
        } else {
            Toast.makeText(mContext, "已经是最新版本", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean needShowUpdateDialog(UpdateInfo updateInfo) {
        int serverVersionCode = updateInfo.versionCode;
        int localVersionCode = AppUtils.getVersionCode(mContext);
        return serverVersionCode > localVersionCode;
    }

    private void downloadApk(UpdateInfo updateInfo) {
        DownloadManagerHelper dmh = new DownloadManagerHelper(mContext);
        if (downloadId != 0) {
            dmh.cancel(downloadId);
        }
        dmh.download(updateInfo);
    }

    public static class Builder {

        private Context mContext;

        private String checkUpdateUrl;

        public Builder(Context context) {
            super();
            mContext = context;
        }

        public Builder checkUpdateUrl(String checkUpdateUrl) {
            this.checkUpdateUrl = checkUpdateUrl;
            return this;
        }

        public UpdateManger build() {
            return new UpdateManger(this);
        }
    }

    private static class UpdateInfo {
        @SerializedName("version_name")
        public String versionName;
        @SerializedName("version_code")
        public int versionCode;
        @SerializedName("must_update")
        public boolean mustUpdate;
        @SerializedName("update_message")
        public String updateMessage;
        @SerializedName("download_url")
        public String downloadUrl;
    }

    /**
     * 下载帮助类，提供下载+取消下载
     */
    public static class DownloadManagerHelper {

        private Context mContext;

        public DownloadManagerHelper(Context context) {
            super();
            mContext = context;
        }

        public long download(UpdateInfo updateInfo) {
            String name = updateInfo.versionName + ".apk";
            Uri uri = Uri.parse(updateInfo.downloadUrl);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setTitle(name);
            request.setDescription(updateInfo.updateMessage);
            request.setMimeType("application/vnd.android.package-archive");
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalFilesDir(mContext, Environment.DIRECTORY_DOWNLOADS, name);
            DownloadManager dm = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
            return dm.enqueue(request);
        }

        public void cancel(long id) {
            DownloadManager dm = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
            dm.remove(id);
        }
    }

    /**
     * 下载状态接收器，下载成功时自动跳转到apk安装界面，点击下载notification跳转到下载界面
     */
    public static class DownloadReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (action.equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                checkDownloadStatus(context, downloadId);
            } else if (action.equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)) {
                Intent downloadIntent = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
                downloadIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(downloadIntent);
            }
        }

        private void checkDownloadStatus(Context context, long downloadId) {
            DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            if (dm != null) {
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(downloadId);
                Cursor cursor = dm.query(query);
                if (cursor.moveToFirst()) {
                    int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                    switch (status) {
                        case DownloadManager.STATUS_PAUSED:
                            Toast.makeText(context, "下载暂停", Toast.LENGTH_SHORT).show();
                        case DownloadManager.STATUS_PENDING:
                            Toast.makeText(context, "下载延迟", Toast.LENGTH_SHORT).show();
                        case DownloadManager.STATUS_RUNNING:
                            Toast.makeText(context, "正在下载", Toast.LENGTH_SHORT).show();
                            break;
                        case DownloadManager.STATUS_SUCCESSFUL:
                            Uri uriForDownloadedFile = dm.getUriForDownloadedFile(downloadId);
                            installApk(context, uriForDownloadedFile);
                            break;
                        case DownloadManager.STATUS_FAILED:
                            Toast.makeText(context, "下载失败", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }
        }

        private void installApk(Context context, Uri apkUri) {
            if (apkUri != null) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }
    }

}
