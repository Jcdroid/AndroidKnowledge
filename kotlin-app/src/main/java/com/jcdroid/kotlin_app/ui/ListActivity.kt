package com.jcdroid.kotlin_app.ui

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.jcdroid.kotlin_app.R
import com.jcdroid.kotlin_app.adapter.ListAdapter

class ListActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(Intent(context, ListActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val listAdapter = ListAdapter(R.layout.item_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = listAdapter
        listAdapter.setNewData(getNames())
    }

    private fun getNames(): MutableList<String>? {
        return mutableListOf("张三", "李四", "王五", "刘六")
    }
}
