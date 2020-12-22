package com.smile2coder.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class MAddress extends BaseModel implements Serializable {

    private Integer userId;

    private String deliveryAddress;

    private String userPhone;

    private String userNickname;

    private static final long serialVersionUID = 1L;

}