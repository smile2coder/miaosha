package com.smile2coder.service.impl;

import com.smile2coder.service.Access;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author zxt
 * @date 12/23/20
 * @desc
 */
public class RandomAccess<T> implements Access<T> {

    private static final float DEFAULT_RANDOM = 1;
    private float random;

    public RandomAccess() {
        this.random = DEFAULT_RANDOM;
    }

    public RandomAccess(float random) {
        if(random < 0 || random > 1) {
            throw new IllegalArgumentException();
        }
        this.random = random;
    }

    @Override
    public boolean access(T o) {
        int i = ThreadLocalRandom.current().nextInt(101);
        return i <= (random * 100);
    }
}
