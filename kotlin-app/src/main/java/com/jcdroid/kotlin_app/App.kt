package com.jcdroid.kotlin_app

import android.app.Application
import com.jcdroid.kotlin_app.data.remote.API
import com.jcdroid.kotlin_app.data.remote.RetrofitInterceptor
import com.jcdroid.kotlin_app.data.remote.RetrofitServiceFactory
import com.jcdroid.kotlin_app.util.AppProfile
import com.jcdroid.kotlin_app.util.FlavorConfigure

/**
 * Created by Jcdroid on 2018/11/15.
 */
class App : Application() {

    companion object {
        private var sInstance: App? = null

        fun getInstance(): App? {
            return sInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        sInstance = this
        initProfile()
        initUtils()
    }

    private fun initUtils() {

    }

    ///////////////////////////////////////////////////////////////////////////
    // get app profile
    ///////////////////////////////////////////////////////////////////////////

    private var appProfile: AppProfile? = null
    fun getAppProfile(): AppProfile? {
        return appProfile
    }

    private fun initProfile() {
        appProfile = FlavorConfigure.newInstance(applicationContext).createProfile()
        appProfile!!.isMock = API.IS_MOCK
    }

    ///////////////////////////////////////////////////////////////////////////
    // get service factory
    ///////////////////////////////////////////////////////////////////////////

    @Volatile
    private var mRetrofitServiceFactory: RetrofitServiceFactory? = null

    fun getServiceFactory(): RetrofitServiceFactory? {
        if (mRetrofitServiceFactory == null) {
            synchronized(App::class.java) {
                if (mRetrofitServiceFactory == null) {
                    mRetrofitServiceFactory = RetrofitServiceFactory.newBuilder(appProfile!!.baseUrl, appProfile!!.isMock).addInterceptor(RetrofitInterceptor()).build()
                }
            }
        }
        return mRetrofitServiceFactory
    }
}