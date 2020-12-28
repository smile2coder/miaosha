package com.smile2coder.controller;

import com.smile2coder.common.ReturnData;
import com.smile2coder.common.ReturnDataUtil;
import com.smile2coder.dto.user.UserRegisterReqDto;
import com.smile2coder.model.MUser;
import com.smile2coder.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zxt
 * @date 12/22/20
 * @desc
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("判断账号是否已存在")
    @GetMapping("/existByUsername")
    public ReturnData existByUsername(@RequestParam(value = "username") String username) {
        boolean exist = this.userService.existByUsername(username);
        return ReturnDataUtil.success(exist);
    }

    @ApiOperation("用户详情")
    @GetMapping("/detail")
    public ReturnData detail() {
        MUser user = this.userService.detail();
        user.setPassword("");
        return ReturnDataUtil.success(user);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ReturnData register(@Valid UserRegisterReqDto userRegisterReqDto, BindingResult result) {
        Integer userId = this.userService.register(userRegisterReqDto);
        return ReturnDataUtil.success(userId);
    }
}
