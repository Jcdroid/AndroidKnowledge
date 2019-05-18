package com.jcdroid.java_app.test;

/**
 * Created by Jcdroid on 2019/1/15.
 */
public class LrcChecker {

    public static void main(String[] args) {
        String[] hex = new String[] {"00", "06", "51", "A3", "0000001F", "03"};
        System.out.println(lrcHexStr(hex));
    }

    public static String lrcHexStr(String[] strings) {
        long all = Long.parseLong("0", 16);
        for (int i = 0; i < strings.length; i++) {
            long one = Long.parseLong(strings[i], 16);
            all = all + one;
        }
        return Long.toHexString(256 - (all % 256));
    }
}
