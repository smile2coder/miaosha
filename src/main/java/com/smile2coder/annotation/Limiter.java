package com.smile2coder.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 * @author zxt
 * @date 12/25/20
 * @desc
 * @see com.smile2coder.aspect.LimiterAspect
 */
@Target({METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Limiter {

    double value() default 5;

}
