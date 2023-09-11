package com.suyh3304.client;

import lombok.Data;

/**
 * Ftp 文件客户端的配置类
 *
 * @author 供应宝
 */
@Data
public class FtpFileClientConfig implements FileClientConfig {

    private String basePath;
    private String domain;
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String mode;

}
