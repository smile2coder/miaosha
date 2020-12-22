package com.smile2coder.holder;

import com.smile2coder.model.MUser;

/**
 * @author zxt
 * @date 12/22/20
 * @desc
 */
public class UserHolder {

    private static ThreadLocal<MUser> userThreadLocal = new ThreadLocal<>();

    public static MUser get() {
        return userThreadLocal.get();
    }

    public static void set(MUser user) {
        userThreadLocal.set(user);
    }

    public static void remove() {
        userThreadLocal.remove();
    }
}
