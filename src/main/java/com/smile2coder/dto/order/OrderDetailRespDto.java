package com.smile2coder.dto.order;

import com.smile2coder.model.BaseModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zxt
 * @date 12/24/20
 * @desc
 */
@Data
@ApiModel("订单详情返回类")
public class OrderDetailRespDto extends BaseModel {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String userNickname;

    private String userPhone;

    private Byte type;

    private String userAddress;

    private BigDecimal actualPrice;

    private BigDecimal payPrice;

    private String goodsName;

    private Integer goodsCount;

    private BigDecimal goodsActualPrice;

    private BigDecimal goodsPayPrice;
}
