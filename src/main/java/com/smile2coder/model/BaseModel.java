package com.smile2coder.model;

import lombok.Data;

import java.util.Date;

/**
 * @author zxt
 * @date 12/22/20
 * @desc
 */
@Data
public class BaseModel {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private Byte status;
}
