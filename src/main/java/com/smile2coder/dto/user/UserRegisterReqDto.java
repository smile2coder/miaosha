package com.smile2coder.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zxt
 * @date 12/25/20
 * @desc
 */
@Data
@ApiModel
public class UserRegisterReqDto {

    @NotNull
    @ApiModelProperty("账号")
    private String username;

    @NotNull
    @ApiModelProperty("密码")
    private String password;

    @NotNull
    @ApiModelProperty("昵称")
    private String nickname;
}
