package com.jcdroid.kotlin_app.data.remote

import android.text.TextUtils
import com.jcdroid.kotlin_app.App
import com.jcdroid.kotlin_app.data.remote.response.LoginRes

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * App内的网络请求统一入口
 *
 *
 * Created by Jcdroid on 2018/10/10.
 */
class NetworkHelper private constructor(){

    ///////////////////////////////////////////////////////////////////////////
    // common method
    ///////////////////////////////////////////////////////////////////////////

    private val simpleService: ISimpleService
        get() = serviceFactory.create<ISimpleService>(ISimpleService::class.java)

    private val serviceFactory: RetrofitServiceFactory
        get() = App.getInstance()!!.getServiceFactory()!!

    ///////////////////////////////////////////////////////////////////////////
    // IAuthService method
    ///////////////////////////////////////////////////////////////////////////

    fun login(): Observable<LoginRes> {
        return simpleService
                .login()
                .compose(this.observableOnMain<LoginRes>())
    }

    private fun <T> observableOnMain(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doFinally { } }
    }

    companion object {

        private var sInstance: NetworkHelper? = null

        val instance: NetworkHelper
            get() {
                if (sInstance == null) {
                    synchronized(NetworkHelper::class.java) {
                        if (sInstance == null) {
                            sInstance = NetworkHelper()
                        }
                    }
                }
                return sInstance!!
            }
    }
}
