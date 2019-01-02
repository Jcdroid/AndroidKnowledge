package com.jcdroid.kotlin_app.data.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Jcdroid on 2018/11/16.
 */
open class BaseResponse {
    @SerializedName("code")
    var code: Int = 0
    @SerializedName("msg")
    var msg: String? = null
}
