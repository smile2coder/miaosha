package com.smile2coder.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class MOrderGoods extends BaseModel implements Serializable {

    private Integer orderId;

    private Integer goodsId;

    private String goodsName;

    private BigDecimal payPrice;

    private BigDecimal actualPrice;

    private Integer count;

    private static final long serialVersionUID = 1L;

}