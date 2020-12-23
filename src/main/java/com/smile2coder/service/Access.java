package com.smile2coder.service;

/**
 * @author zxt
 * @date 12/23/20
 * @desc
 */
public interface Access<T> {

    boolean access(T t);
}
