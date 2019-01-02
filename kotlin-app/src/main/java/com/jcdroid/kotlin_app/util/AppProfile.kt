package com.jcdroid.kotlin_app.util

import android.content.Context

/**
 * Created by livingmagic on 2017/12/10.
 */

class AppProfile(context: Context, private val value: Int, val baseUrl: String, val webUrl: String, var shareUrl: String?) {
    var isMock = false
    val applicationContext: Context

    val isProd: Boolean
        get() = value == PROD

    val isAlpha: Boolean
        get() = value == LOCAL

    init {
        this.applicationContext = context.applicationContext
    }

    companion object {
        @JvmField val LOCAL = 0
        @JvmField val PROD = 1
    }
}
