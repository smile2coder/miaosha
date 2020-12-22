package com.smile2coder.exception;

/**
 * @author zxt
 * @date 12/22/20
 * @desc
 */
public class UnKnowAccountException extends CommonException {
    public UnKnowAccountException() {
    }

    public UnKnowAccountException(String format, Object... args) {
        super(format, args);
    }
}
