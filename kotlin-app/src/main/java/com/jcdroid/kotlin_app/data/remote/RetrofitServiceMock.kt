package com.jcdroid.kotlin_app.data.remote

import java.lang.annotation.Inherited
import kotlin.reflect.KClass

/**
 * Created by livingmagic on 2017/12/11.
 */

@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
@Inherited
annotation class RetrofitServiceMock(val mock: KClass<*>)