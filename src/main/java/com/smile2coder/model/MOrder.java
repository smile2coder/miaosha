package com.smile2coder.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class MOrder extends BaseModel implements Serializable {

    private Integer userId;

    private String userNickname;

    private String userPhone;

    private Byte type;

    private String userAddress;

    private BigDecimal actualPrice;

    private BigDecimal payPrice;

    private static final long serialVersionUID = 1L;

}