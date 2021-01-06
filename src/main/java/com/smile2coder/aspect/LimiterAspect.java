package com.smile2coder.aspect;

import com.smile2coder.exception.RequestTooManyException;
import com.smile2coder.holder.UserHolder;
import com.smile2coder.model.MUser;
import com.smile2coder.service.Limiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zxt
 * @date 12/25/20
 * @desc
 */
@Aspect
@Component
public class LimiterAspect {

    @Autowired
    private Limiter limiter;

    @Pointcut(value = "@annotation(com.smile2coder.annotation.Limiter)")
    public void pointcut() {
    }

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature)pjp.getSignature();
        Method method = signature.getMethod();
        com.smile2coder.annotation.Limiter annotation = method.getAnnotation(com.smile2coder.annotation.Limiter.class);
        double permitsPerSecond = annotation.value();

        MUser user = UserHolder.get();

        if (!limiter.tryAcquire(user.getId(), permitsPerSecond)) {
            throw new RequestTooManyException();
        }
        return pjp.proceed();
    }
}
