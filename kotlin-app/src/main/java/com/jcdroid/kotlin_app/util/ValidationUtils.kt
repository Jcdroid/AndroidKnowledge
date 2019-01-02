package com.jcdroid.kotlin_app.util

import java.util.regex.Pattern

/**
 * 字符串格式验证工具：
 * *
 *  * 验证自然数
 *  * 银行卡账号
 *  * 验证手机号码
 *  * 验证邮箱
 *  * 验证验证码
 *  * 验证密码
 *  * 验证店铺名
 *  * 验证身份证号码
 *  * 验证价格、运费
 *  * 验证路径
 * *
 * Created by Jcdroid on 2018/10/10.
 */
object ValidationUtils {

    /**
     * 验证自然数
     * @param text
     * @return true is natural number, otherwise false
     */
    fun isNaturalNumber(text: String): Boolean {
        val pattern = Pattern.compile("(0|^[1-9]\\d*$)")
        val matcher = pattern.matcher(text)
        return matcher.matches()
    }

    /**
     * 验证银行卡账号
     * @param text
     * @return true is bank card no, otherwise false
     */
    fun isBankCardNo(text: String): Boolean {
        val pattern = Pattern.compile("(^\\d{16}$)|(^\\d{19}$)")
        val matcher = pattern.matcher(text)
        return matcher.matches()
    }

    /**
     * 验证手机号码
     * @param text
     * @return true is phone no, otherwise false
     */
    fun isPhoneNo(text: String): Boolean {
        val pattern = Pattern.compile("^(1[3-9][0-9])[0-9]{8}$")
        val matcher = pattern.matcher(text)
        return matcher.matches()
    }

    /**
     * 验证邮箱
     * @param text
     * @return true is email, otherwise false
     */
    fun isEmail(text: String): Boolean {
        val pattern = Pattern.compile("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?")
        val matcher = pattern.matcher(text)
        return matcher.matches()
    }

    /**
     * 验证验证码
     * @param text
     * @return true is captcha, otherwise false
     */
    fun isCaptcha(text: String): Boolean {
        val pattern = Pattern.compile("^\\d{4}$")
        val matcher = pattern.matcher(text)
        return matcher.matches()
    }

    /**
     * 验证密码（长度和格式）
     * @param text
     * @return true is password, otherwise false
     */
    fun isPassword(text: String): Boolean {
        val pattern = Pattern.compile("^.{6,20}$")
        val matcher = pattern.matcher(text)
        return matcher.matches()
    }

    /**
     * 判断名字只有中文、字母和数字
     * @param text
     * @return true is name, otherwise false
     */
    fun isName(text: String): Boolean {
        val pattern = Pattern.compile("^[A-Za-z\\u4e00-\\u9fa5\\d\\s]+$")
        return pattern.matcher(text).matches()
    }

    /**
     * 验证身份证号码
     * @param text
     * @return true is id number, otherwise false
     */
    fun isIDNumber(text: String): Boolean {
        val pattern = Pattern.compile("(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)")
        return pattern.matcher(text).matches()
    }

    /**
     * 验证价格
     * @param text
     * @return true is price, otherwise false
     */
    fun isPrice(text: String): Boolean {
        val pattern = Pattern.compile("^(-?\\d+)(.\\d+)?$")
        return pattern.matcher(text).matches()
    }

    /**
     * 判断是否为网页路径
     * @param url
     * @return true is url, otherwise false
     */
    fun isUrl(url: String): Boolean {
        val pattern = Pattern.compile("[a-zA-z]+://[^\\s]*")
        return pattern.matcher(url).matches()
    }
}
