package com.jcdroid.kotlin_app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val testBtn = findViewById<Button>(R.id.btn_test)
        testBtn.setOnClickListener {
            Toast.makeText(this, "Hello Kotlin", Toast.LENGTH_LONG).show()
        }
    }
}
