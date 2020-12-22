package com.smile2coder.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MUser extends BaseModel implements Serializable {

    private String username;

    private String password;

    private String nickname;

    private String salt;

    private Date lastLoginTime;

    private static final long serialVersionUID = 1L;

}