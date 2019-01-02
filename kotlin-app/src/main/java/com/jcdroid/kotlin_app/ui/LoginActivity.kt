package com.jcdroid.kotlin_app.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.jcdroid.kotlin_app.R
import com.jcdroid.kotlin_app.data.remote.NetworkHelper
import com.jcdroid.kotlin_app.data.remote.SimpleSubscriber
import com.jcdroid.kotlin_app.data.remote.response.LoginRes
import com.jcdroid.kotlin_app.util.ValidationUtils
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<LoginActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login.setOnClickListener {
            if (isValid()) {
                NetworkHelper.instance.login().subscribe(object : SimpleSubscriber<LoginRes>() {
                    override fun onSuccess(t: LoginRes) {
                        toast("登录成功")
                    }

                    override fun onFailed(t: LoginRes?, throwable: Throwable) {
                        toast("登录失败")
                    }

                })
            }
        }
    }

    fun isValid(): Boolean {
        val email = tv_email.text.toString()
        val password = tv_password.text.toString()
        val isEmailEmpty = TextUtils.isEmpty(email)
        val isEmail = ValidationUtils.isEmail(email)
        val isPasswordEmpty = TextUtils.isEmpty(password)
        val isPassword = ValidationUtils.isPassword(password)
        if (isEmailEmpty) {
            toast("邮箱不能为空")
        } else if (!isEmail) {
            toast("邮箱格式不正确")
        } else if (isPasswordEmpty) {
            toast("密码不能为空")
        } else if (!isPassword) {
            toast("密码格式不正确")
        }
        return !isEmailEmpty and !isPasswordEmpty and isEmail and isPassword
    }
}
