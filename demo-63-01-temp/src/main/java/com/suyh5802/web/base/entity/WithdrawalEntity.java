package com.suyh5802.web.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author suyh
 * @since 2023-12-27
 */
@TableName("tb_withdrawal")
@Data
public class WithdrawalEntity extends BaseMqVo {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String uid;
    private Long ctime;
    private BigDecimal amount;
    private String channel;
    private Long vungoWithdrawalId;
    private String originChannel;
    private String gaid;
    private Long day;
    @TableField("`order`")
    private String order;
    private Long cts;
    private String pn;
    private Long mtime;
    private String loginChannel;
    private String registerChannel;
}
