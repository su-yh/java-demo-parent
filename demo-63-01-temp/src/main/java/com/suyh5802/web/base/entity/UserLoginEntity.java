package com.suyh5802.web.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author suyh
 * @since 2023-12-27
 */
@TableName("tb_user_login")
@Data
public class UserLoginEntity extends BaseMqVo {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String uid;
    private Integer src;
    private String channel;
    private Long ctime; // 原来这个值的单位是s
    private String gaid;
    private String originChannel;
    private Long vungoUserLoginId;
    private Long day;
    private Long cts;
    private String pn;
}
