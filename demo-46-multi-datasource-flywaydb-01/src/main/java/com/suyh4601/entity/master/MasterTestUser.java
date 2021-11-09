package com.suyh4601.entity.master;

import com.suyh4601.entity.TestUserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MasterTestUser extends TestUserEntity {
    private static final long serialVersionUID = -5246834046710354251L;
}
