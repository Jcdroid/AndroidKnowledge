package com.jcdroid.java_app.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Jcdroid on 2018/11/28.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
public @interface SimpleAnnotation {
    String date() default "2018/12/01";
}
