package com.smile2coder.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author LKK
 * Date on 2020/8/17  下午 4:25
 */
@Getter
@AllArgsConstructor
public enum ReturnCodeEnum {

    SUCCESS(200, "成功"),
    ERROR(500, "失败"),
    UN_SAFE(504, "未修改初始密码"),
    INCORRECT_CREDENTIALS(505, "账号或密码错误"),
    LOCKED_ACCOUNT(506, "该用户已被禁用"),
    UNKNOWN_ACCOUNT(507, "该用户不存在"),
    ERROR_TOKEN(509, "Token失效请重新登录"),
    UN_LOGIN(510, "用户未登录"),
    UN_AUTH(515, "用户没有权限"),
    LOGIN_OUT(520, "用户退出登录"),
    ;

    private int code;
    private String desc;
}
