package com.smile2coder.holder;


/**
 * @author zxt
 * @date 12/22/20
 * @desc
 */
public class TokenHolder {

    private static ThreadLocal<String> userThreadLocal = new ThreadLocal<>();

    public static String get() {
        return userThreadLocal.get();
    }

    public static void set(String token) {
        userThreadLocal.set(token);
    }

    public static void remove() {
        userThreadLocal.remove();
    }
}
