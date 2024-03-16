package com.suyh.mp.dynamic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户的个人信息，一些基本数据
 */
@TableName("personal_info")
@Data
public class PersonalUniversalEntity {
    @TableId(type = IdType.NONE)
    private String uuid;

    private String country;
    private String city;
    private String workExperience;
    private String aboutMe;

    private String discord;
    private String huggingFace;
    private String github;
    private String reddit;
}
