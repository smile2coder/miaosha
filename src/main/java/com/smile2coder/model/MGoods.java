package com.smile2coder.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class MGoods extends BaseModel implements Serializable {

    private String name;

    private String subname;

    private BigDecimal actualPrice;

    private BigDecimal payPrice;

    private Integer stock;

    private Date startTime;

    private Date endTime;

    private static final long serialVersionUID = 1L;

}