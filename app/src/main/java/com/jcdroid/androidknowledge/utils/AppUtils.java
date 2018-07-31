package com.jcdroid.androidknowledge.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Jcdroid on 2018/7/11.
 */
public class AppUtils {

    /**
     * 获取APP的VersionName
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        String packageName = context.getPackageName();
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? null : pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取APP的VersionCode
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        String packageName = context.getPackageName();
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? -1 : pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
