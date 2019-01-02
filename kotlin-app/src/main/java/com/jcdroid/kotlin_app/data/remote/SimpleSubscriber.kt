package com.jcdroid.kotlin_app.data.remote

import io.reactivex.observers.DisposableObserver

/**
 * Created by Jcdroid on 2018/10/10.
 */
abstract class SimpleSubscriber<T> : DisposableObserver<T>() {

    abstract fun onSuccess(t: T)
    abstract fun onFailed(t: T?, throwable: Throwable)

    override fun onStart() {
        super.onStart()
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(t: Throwable) {
        onFailed(null, t)
    }

    override fun onComplete() {

    }
}
