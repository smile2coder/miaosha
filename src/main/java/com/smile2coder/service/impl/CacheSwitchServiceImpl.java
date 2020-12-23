package com.smile2coder.service.impl;

import com.smile2coder.service.SwitchService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zxt
 **/
public class CacheSwitchServiceImpl implements SwitchService<Integer> {

    private static Map<Integer, Boolean> cache = new ConcurrentHashMap<>();

    @Override
    public boolean isOpen(Integer integer, Boolean defaultValue) {
        return cache.getOrDefault(integer, defaultValue);
    }

    @Override
    public void setSwitch(Integer integer, Boolean b) {
        cache.put(integer, b);
    }
}
