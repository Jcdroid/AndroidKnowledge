package com.jcdroid.androidknowledge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jcdroid.androidknowledge.annotations.ServiceConstant;
import com.jcdroid.androidknowledge.utils.ServiceUtils;
import com.jcdroid.androidknowledge.utils.UpdateManger;

public class MainActivity extends AppCompatActivity {

    @ServiceConstant
    public static String ACTION_VIEW_CRIME;

    static {
        ServiceUtils.populateConstants(MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btn);
        TextView textView = findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UpdateManger.Builder(MainActivity.this)
                        .checkUpdateUrl("http://www.wanandroid.com/tools/mockapi/3617/download")
                        .build()
                        .checkUpdate();
            }
        });
        textView.setText(ACTION_VIEW_CRIME);
    }
}
