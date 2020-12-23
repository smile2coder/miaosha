package com.smile2coder.dto.order;

import com.smile2coder.annotation.Phone;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author zxt
 * @date 12/22/20
 * @desc
 */
@Data
@ApiModel
public class OrderReqDto {

    @NotNull
    @ApiModelProperty("用户昵称")
    private String userNickname;

    @Phone
    @ApiModelProperty("用户手机号")
    private String userPhone;

    @ApiModelProperty("订单类型")
    private Byte type;

    @NotNull
    @ApiModelProperty("收获地址")
    private String userAddress;

    @NotNull
    @ApiModelProperty("商品ID")
    private Integer goodsId;

    @Min(value = 1, message = "商品数量最少为1")
    @ApiModelProperty("商品数量")
    private Integer count;
}
