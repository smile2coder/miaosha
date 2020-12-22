package com.smile2coder.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author zxt
 * @date 2020-12-22
 * @desc 对手机号做验证
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {
    private Phone phone;
    private final String pattern = "/^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/";

    @Override
    public void initialize(Phone constraintAnnotation) {
        this.phone = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) {
            return false;
        }
        String pattern = phone.pattern();
        if(pattern == null || "".equals(pattern)) {
            pattern = this.pattern;
        }
        return Pattern.compile(pattern).matcher(value).matches();
    }
}
