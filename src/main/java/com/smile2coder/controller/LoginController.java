package com.smile2coder.controller;

import com.smile2coder.common.ReturnData;
import com.smile2coder.common.ReturnDataUtil;
import com.smile2coder.dto.login.LoginReqDto;
import com.smile2coder.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author zxt
 * @date 12/22/20
 * @desc
 */
@RestController
@Api(tags = "登录相关接口")
public class LoginController {

    @Autowired
    private UserService userService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public ReturnData login(@Valid LoginReqDto loginReqDto, BindingResult result) {
        String token = this.userService.login(loginReqDto);
        return ReturnDataUtil.success(token);
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    public ReturnData logout() {
        Integer userId = this.userService.logout();
        return ReturnDataUtil.success(userId);
    }
}
