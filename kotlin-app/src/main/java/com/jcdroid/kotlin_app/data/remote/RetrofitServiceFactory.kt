package com.jcdroid.kotlin_app.data.remote

import android.text.TextUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.reflect.full.primaryConstructor

/**
 * Created by Jcdroid on 2018/9/29.
 */
class RetrofitServiceFactory private constructor() {

    private var mRetrofit: Retrofit? = null

    private var isMock: Boolean = false

    /**
     * Create an implementation of the API endpoints defined by the service interface or mock service.
     * @param service service class, mock mode need set [com.triumen.libutils.AppProfile.setMock] true.
     */
    @Suppress("UNCHECKED_CAST")
    fun <T> create(service: Class<T>): T {
        return if (isMock) {
            try {
                val annotation = service.getAnnotation(RetrofitServiceMock::class.java)
                if (annotation != null) {
                    val mockAuth = annotation.mock
                    mockAuth.primaryConstructor?.call() as T
                } else {
                    throw RuntimeException("It's currently mock mode, you must be give this service @RetrofitServiceMock(mock = XXXMock.class) in mock mode.")
                }
            } catch (e: InstantiationException) {
                throw RuntimeException("InstantiationException or IllegalAccessException when buildFactory")
            } catch (e: IllegalAccessException) {
                throw RuntimeException("InstantiationException or IllegalAccessException when buildFactory")
            }

        } else {
            mRetrofit!!.create(service)
        }
    }

    class Builder internal constructor(private val baseUrl: String, private val isMock: Boolean) {
        private val okHttpClientBuilder: OkHttpClient.Builder

        init {
            if (TextUtils.isEmpty(baseUrl)) {
                throw IllegalArgumentException("baseUrl must not be null.")
            }
            okHttpClientBuilder = OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
        }

        fun addInterceptor(interceptor: Interceptor): Builder {
            okHttpClientBuilder.addInterceptor(interceptor)
            return this
        }

        fun build(): RetrofitServiceFactory {
            val retrofitServiceFactory = RetrofitServiceFactory()
            retrofitServiceFactory.mRetrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            retrofitServiceFactory.isMock = isMock
            return retrofitServiceFactory
        }
    }

    companion object {

        fun newBuilder(baseUrl: String, isMock: Boolean): Builder {
            return Builder(baseUrl, isMock)
        }
    }
}
