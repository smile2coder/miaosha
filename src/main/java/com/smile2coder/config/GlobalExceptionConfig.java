package com.smile2coder.config;

import com.smile2coder.common.ReturnCodeEnum;
import com.smile2coder.common.ReturnData;
import com.smile2coder.common.ReturnDataUtil;
import com.smile2coder.exception.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zxt
 * @date 12/21/20
 * @desc
 */
@RestControllerAdvice
public class GlobalExceptionConfig {

    @ExceptionHandler(value = RequestTooManyException.class)
    public ReturnData<String> exception(RequestTooManyException e) {
        return ReturnDataUtil.result(ReturnCodeEnum.REQUEST_TOO_MANY);
    }

    @ExceptionHandler(value = JoinUserToManyException.class)
    public ReturnData<String> exception(JoinUserToManyException e) {
        return ReturnDataUtil.result(ReturnCodeEnum.JOIN_USER_TO_MANY);
    }

    @ExceptionHandler(value = RepeatJoinException.class)
    public ReturnData<String> exception(RepeatJoinException e) {
        return ReturnDataUtil.result(ReturnCodeEnum.REPEAT_JOIN);
    }

    @ExceptionHandler(value = GoodsFinshException.class)
    public ReturnData<String> exception(GoodsFinshException e) {
        return ReturnDataUtil.result(ReturnCodeEnum.GOODS_FINSH);
    }

    @ExceptionHandler(value = UnLoginException.class)
    public ReturnData<String> exception(UnLoginException e) {
        return ReturnDataUtil.result(ReturnCodeEnum.ERROR_TOKEN);
    }

    @ExceptionHandler(value = UnKnowAccountException.class)
    public ReturnData<String> exception(UnKnowAccountException e) {
        return ReturnDataUtil.result(ReturnCodeEnum.UNKNOWN_ACCOUNT);
    }

    @ExceptionHandler(value = CommonException.class)
    public ReturnData<String> exception(CommonException e) {
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
