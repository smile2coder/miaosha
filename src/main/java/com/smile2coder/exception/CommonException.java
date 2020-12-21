package com.smile2coder.exception;

/**
 * @author zxt
 * @date 12/21/20
 * @desc 一般的异常
 */
public class CommonException extends Exception {

    public CommonException(String format, Object... args) {
        super(String.format(format, args));
    }
}
