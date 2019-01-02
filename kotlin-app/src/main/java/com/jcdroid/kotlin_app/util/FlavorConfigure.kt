package com.jcdroid.kotlin_app.util

import android.content.Context
import com.jcdroid.kotlin_app.FlavorConfigureImpl

/**
 * Created by livingmagic on 2017/12/11.
 */

abstract class FlavorConfigure {

    abstract fun createProfile(): AppProfile

    companion object {
        fun newInstance(context: Context): FlavorConfigure {
            return FlavorConfigureImpl(context)
        }
    }
}
