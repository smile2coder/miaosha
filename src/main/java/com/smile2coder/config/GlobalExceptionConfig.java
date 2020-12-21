package com.smile2coder.config;

import com.smile2coder.common.ReturnData;
import com.smile2coder.common.ReturnDataUtil;
import com.smile2coder.exception.CommonException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zxt
 * @date 12/21/20
 * @desc
 */
@RestControllerAdvice
public class GlobalExceptionConfig {

    @ExceptionHandler(value = CommonException.class)
    public ReturnData<String> ecException(CommonException e) {
        e.printStackTrace();
        String message = e.getMessage();
        return ReturnDataUtil.error(message);
    }

    @ExceptionHandler(value = Exception.class)
    public ReturnData<String> exception(Exception e) {
        e.printStackTrace();
        return ReturnDataUtil.error();
    }
}
