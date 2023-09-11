package com.suyh3304.client;

import lombok.Data;

/**
 * 本地文件客户端的配置类
 *
 * @author 供应宝
 */
@Data
public class LocalFileClientConfig implements FileClientConfig {

    /**
     * 基础路径
     */
    private String basePath;

    /**
     * 自定义域名
     */
    private String domain;

}
