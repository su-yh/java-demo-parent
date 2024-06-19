package com.suyh1101.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import com.suyh1101.constant.CommonConstant;


/**
 * @author suyh
 * @since 2024-06-11
 */
@Data
public abstract class AbstractDeletedFlagEntity {
    // 删除标记，0：未删除，非0：已删除(一般记时间戳，以纳秒为单位)
//    @Null(message = "deleteFlag 必须为null")
//    @ApiModelProperty("删除标记，0：未删除，非0：已删除(一般记时间戳，以纳秒为单位)")
    private Long deleteFlag;

    // 删除标记：是否已经被删除
    @JsonIgnore
    public boolean isDeleted() {
        return !CommonConstant.FLAG_EXISTS.equals(deleteFlag);
    }
}
