package com.suyh4601.entity.slave;

import com.suyh4601.entity.TestUserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SlaveTestUser extends TestUserEntity {
    private static final long serialVersionUID = -8407019326079176358L;
}
