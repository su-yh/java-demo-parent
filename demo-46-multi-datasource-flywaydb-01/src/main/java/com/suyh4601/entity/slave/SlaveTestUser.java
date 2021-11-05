package com.suyh4601.entity.slave;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.suyh4601.entity.TestUserEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("test_user")
public class SlaveTestUser extends TestUserEntity {
    private static final long serialVersionUID = -8407019326079176358L;
}
