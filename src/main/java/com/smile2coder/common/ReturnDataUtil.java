package com.smile2coder.common;

/**
 * @Author LKK
 * Date on 2020/8/17  下午 4:25
 */
public class ReturnDataUtil {
    public static ReturnData result(ReturnCodeEnum returnCodeEnum) {
        return new ReturnData<>(returnCodeEnum.getCode(), returnCodeEnum.getDesc(), null);
    }

    public static <T> ReturnData result(ReturnCodeEnum returnCodeEnum, T data) {
        return new ReturnData<>(returnCodeEnum.getCode(), returnCodeEnum.getDesc(), data);
    }

    public static ReturnData success() {
        return result(ReturnCodeEnum.SUCCESS);
    }

    public static <T> ReturnData success(String reason, T data) {
        return new ReturnData<>(ReturnCodeEnum.SUCCESS.getCode(), reason, data);
    }

    public static <T> ReturnData success(T data) {
        return new ReturnData<>(ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getDesc(), data);
    }

    public static ReturnData error() {
        return result(ReturnCodeEnum.ERROR);
    }

    public static ReturnData error(String reason) {
        return new ReturnData(ReturnCodeEnum.ERROR.getCode(), reason);
    }

}
