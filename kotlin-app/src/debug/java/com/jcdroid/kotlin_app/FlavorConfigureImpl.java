package com.jcdroid.kotlin_app;

import android.content.Context;

import com.jcdroid.kotlin_app.data.remote.API;
import com.jcdroid.kotlin_app.util.AppProfile;
import com.jcdroid.kotlin_app.util.FlavorConfigure;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Jcdroid on 2018/10/10.
 */
public class FlavorConfigureImpl extends FlavorConfigure {

    private Context context;
    public FlavorConfigureImpl(Context context) {
        this.context = context;
    }

    @NotNull
    @Override
    public AppProfile createProfile() {
        return new AppProfile(context, AppProfile.LOCAL, API.HOST, "", "");
    }
}
