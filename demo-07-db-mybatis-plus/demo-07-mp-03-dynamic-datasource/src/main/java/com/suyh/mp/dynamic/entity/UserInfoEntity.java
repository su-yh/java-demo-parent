package com.suyh.mp.dynamic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("user_info")
@Data
public class UserInfoEntity {
    @TableId(type = IdType.NONE)
    private String uuid;
    private String userName;
    private String phone;
    private String email;
    private String phoneVerifyCode;
    private Date phoneVerifyCodeExpired;
    private String emailVerifyCode;
    private Date emailVerifyCodeExpired;

    private Date createDate;
    private String createBy;
    private Date updateDate;
    private String updateBy;
}
