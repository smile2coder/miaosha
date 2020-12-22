package com.smile2coder.dto.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zxt
 * @date 12/22/20
 * @desc
 */
@Data
@ApiModel
public class LoginReqDto {

    @ApiModelProperty("账号")
    @NotNull(message = "账号不能为空")
    private String username;

    @ApiModelProperty("密码")
    @NotNull(message = "密码不能为空")
    private String password;
}
