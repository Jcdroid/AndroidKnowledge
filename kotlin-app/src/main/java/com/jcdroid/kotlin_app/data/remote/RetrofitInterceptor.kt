package com.jcdroid.kotlin_app.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by Jcdroid on 2018/9/30.
 */
class RetrofitInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val headers = request.headers().newBuilder()
                .add("B-ACCESS-TOKEN", "")
                .build()
        request = request.newBuilder().headers(headers).build()
        return chain.proceed(request)

    }
}
