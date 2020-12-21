package com.smile2coder.common;

import lombok.Data;

/**
 * @Author LKK
 * Date on 2020/8/17  下午 4:24
 */
@Data
public class ReturnData<T> {
    private Integer code;
    private String reason;
    private T data;

    public ReturnData() {
    }

    public ReturnData(Integer code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public ReturnData(Integer code, String reason, T data) {
        this.code = code;
        this.reason = reason;
        this.data = data;
    }

    public ReturnData(ReturnCodeEnum error) {
        this.code = error.getCode();
        this.reason = error.getDesc();
    }
}
