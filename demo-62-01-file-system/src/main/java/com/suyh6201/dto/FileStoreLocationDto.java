package com.suyh6201.dto;

import lombok.Data;

/**
 * 文件存储位置结果
 *
 * @author suyh
 * @since 2023-12-01
 */
@Data
public class FileStoreLocationDto {
    // 文件保存位置，对于本地存储的文件生成的url，不包含协议域名以及上下文根路径，需要前端自行拼接成完整url。
    private String url;
}
