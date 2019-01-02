package com.jcdroid.kotlin_app.data.remote;

import com.jcdroid.kotlin_app.data.remote.response.LoginRes;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Jcdroid on 2018/11/16.
 */
public interface ISimpleService {

    @GET("/tools/mockapi/3617/login")
    Observable<LoginRes> login();

}
