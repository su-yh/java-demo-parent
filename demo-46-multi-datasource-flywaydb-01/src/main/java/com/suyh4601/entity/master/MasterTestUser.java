package com.suyh4601.entity.master;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.suyh4601.entity.TestUserEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("test_user")
public class MasterTestUser extends TestUserEntity {
    private static final long serialVersionUID = -5246834046710354251L;
}
