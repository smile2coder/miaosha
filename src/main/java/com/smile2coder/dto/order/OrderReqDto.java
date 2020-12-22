package com.smile2coder.dto.order;

import com.smile2coder.annotation.Phone;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zxt
 * @date 12/22/20
 * @desc
 */
@Data
public class OrderReqDto {

    @NotNull
    private String userNickname;
    @Phone
    private String userPhone;
    private Byte type;
    @NotNull
    private String userAddress;
    @NotNull
    private Integer goodsId;
    @Min(value = 1, message = "商品数量最少为1")
    private Integer count;
}
