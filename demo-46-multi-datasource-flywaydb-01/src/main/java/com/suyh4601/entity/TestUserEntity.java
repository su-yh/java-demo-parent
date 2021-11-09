/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2020. All rights reserved.
 */

package com.suyh4601.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sWX5327794
 * @since 2021-11-05
 */
@Data
public class TestUserEntity implements Serializable {

    private static final long serialVersionUID = 2382487815182020002L;

    /** id */
    private Long id;
    /** 姓名 */
    private String name;
    /** 手机号 */
    private String phone;
    /** 职称职别 */
    private String title;
    /** 邮箱 */
    private String email;
    /** 性别 */
    private String gender;
    /** 出生时间 */
    private Date dateOfBirth;
    /** 1:已删除,0:未删除 */
    private Boolean deleted;
    /** 创建时间 */
    private Date sysCreateTime;
    /** 创建人 */
    private String sysCreateUser;
    /** 更新时间 */
    private Date sysUpdateTime;
    /** 更新人 */
    private String sysUpdateUser;
    /** 版本号 */
    private Long recordVersion;
}
