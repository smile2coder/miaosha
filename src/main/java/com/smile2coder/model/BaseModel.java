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

    /**
     * 正常
     */
    public static final byte STATUS_NORMAL = 0;
    /**
     * 删除
     */
    public static final byte STATUS_DELETED = -1;

    private Integer id;

    private Date createTime;

    private Date updateTime;

    private Byte status;
}
