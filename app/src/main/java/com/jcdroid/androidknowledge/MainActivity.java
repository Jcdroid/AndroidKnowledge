package com.jcdroid.androidknowledge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jcdroid.androidknowledge.utils.UpdateManger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UpdateManger.Builder(MainActivity.this)
                        .checkUpdateUrl("http://www.wanandroid.com/tools/mockapi/3617/download")
                        .build()
                        .checkUpdate();
            }
        });
    }
}
