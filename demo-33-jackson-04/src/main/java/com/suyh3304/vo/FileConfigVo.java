package com.suyh3304.vo;

import com.suyh3304.client.FileClientConfig;
import lombok.Data;

/**
 * 文件配置表
 *
 * @author 供应宝
 */
@Data
public class FileConfigVo {
    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 配置编号，数据库自增
     */
    private Long id;
    /**
     * 配置名
     */
    private String name;
    private Integer storage;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否为主配置
     *
     * 由于我们可以配置多个文件配置，默认情况下，使用主配置进行文件的上传
     */
    private Boolean master;

    /**
     * 支付渠道配置
     */
    private FileClientConfig config;

}
