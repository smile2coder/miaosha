package com.smile2coder.exception;

/**
 * @author zxt
 * @date 12/22/20
 * @desc
 */
public class UnLoginException extends CommonException {
    public UnLoginException() {
    }

    public UnLoginException(String format, Object... args) {
        super(format, args);
    }
}
