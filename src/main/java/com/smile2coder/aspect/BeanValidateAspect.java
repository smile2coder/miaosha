package com.smile2coder.aspect;

import com.smile2coder.common.ReturnCodeEnum;
import com.smile2coder.common.ReturnData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * @author zxt
 * @date 12/22/20
 * @desc
 */
@Aspect
@Component
public class BeanValidateAspect {

    @Pointcut(value = "execution(public * com.smile2coder.controller.*Controller.*(..))")
    public void pointcut() {
    }

    @Around(value = "pointcut() && args(.., result)", argNames = "result")
    public Object around(ProceedingJoinPoint pjp, BindingResult result) throws Throwable {
        if(result.hasErrors()) {
            String message = result.getFieldError().getDefaultMessage();
            return new ReturnData<>(ReturnCodeEnum.ERROR.getCode(), message, null);
        }
        return pjp.proceed();
    }

}
