package com.smile2coder.controller;

import com.smile2coder.common.ReturnData;
import com.smile2coder.common.ReturnDataUtil;
import com.smile2coder.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation("判断用户名是否已存在")
    @GetMapping("/existByUsername")
    public ReturnData existByUsername(@RequestParam("username") String username) {
        boolean exist = this.userService.existByUsername(username);
        return ReturnDataUtil.success(exist);
    }

    @ApiOperation("用户详情")
    @GetMapping("/{userId}")
    public ReturnData detail() {
        return ReturnDataUtil.success();
    }
}
