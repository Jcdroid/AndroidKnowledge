package com.jcdroid.kotlin_app.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jcdroid.kotlin_app.data.entity.UserInfo

/**
 * Created by Jcdroid on 2018/11/16.
 */
class LoginRes : BaseResponse() {
    @SerializedName("data")
    var data: UserInfo? = null
}
