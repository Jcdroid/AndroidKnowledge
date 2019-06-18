package com.jcdroid.androidknowledge.utils;

import android.util.Log;

import com.jcdroid.androidknowledge.annotations.ServiceConstant;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by Jcdroid on 2018/11/27.
 */
public class ServiceUtils {

    private static final String TAG = ServiceUtils.class.getSimpleName();

    public static void populateConstants(Class<?> clazz) {
        String packageName = clazz.getPackage().getName();
        for (Field field : clazz.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers()) &&
                    field.isAnnotationPresent(ServiceConstant.class)) {
                String value = packageName + "." + field.getName();
                try {
                    field.set(null, value);
                    Log.i(TAG, "Setup service constant: " + value + "");
                } catch (IllegalAccessException iae) {
                    Log.e(TAG, "Unable to setup constant for field " +
                            field.getName() +
                            " in class " + clazz.getName());
                }
            }
        }
    }
}
