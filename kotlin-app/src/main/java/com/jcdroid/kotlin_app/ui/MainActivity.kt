package com.jcdroid.kotlin_app.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.jcdroid.kotlin_app.R
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_toast).setOnClickListener {
            toast("Hello Kotlin")
        }
        findViewById<Button>(R.id.btn_login).setOnClickListener {
            LoginActivity.start(this)
        }
        findViewById<Button>(R.id.btn_list).setOnClickListener {
            ListActivity.start(this)
        }
    }
}
