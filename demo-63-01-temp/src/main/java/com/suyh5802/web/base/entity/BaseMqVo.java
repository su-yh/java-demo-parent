package com.suyh5802.web.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author suyh
 * @since 2023-12-28
 */
@Data
public abstract class BaseMqVo {
    // 非数据库属性，而是业务添加属性
    @TableField(exist = false)
    private Long correlationId;
}
