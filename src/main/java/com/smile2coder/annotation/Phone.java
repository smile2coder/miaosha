package com.smile2coder.annotation;

/**
 * @author ZxT
 * @date 2020-01-17
 * @desc
 */

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Target({FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidator.class)
public @interface Phone {

    String message() default "手机号格式不正确";

    String pattern() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
