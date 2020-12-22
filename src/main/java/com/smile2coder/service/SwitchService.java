package com.smile2coder.service;

/**
 * @author zxt
 **/
public interface SwitchService<T> {

    boolean isOpen(T t, Boolean defaultValue);

    void setSwitch(T t, Boolean b);
}
