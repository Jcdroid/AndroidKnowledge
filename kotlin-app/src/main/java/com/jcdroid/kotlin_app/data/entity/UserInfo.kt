package com.jcdroid.kotlin_app.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Jcdroid on 2018/11/16.
 */
class UserInfo {
    @SerializedName("name")
    var name: String? = null
    @SerializedName("age")
    var age: Int = 0
}
