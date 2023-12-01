package com.suyh6201.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * @author suyh
 * @since 2023-11-28
 */
@ConfigurationProperties(VcsProperties.PREFIX)
@Validated
@Data
public class VcsProperties {
    public static final String PREFIX = "suyh.fs";

    @Valid
    private final FileStoreConfig fileStoreConfig = new FileStoreConfig();

    @Data
    @Validated
    public static class FileStoreConfig {
        /**
         * 如果存在系统磁盘，则指定系统磁盘的存储位置
         */
        @NotEmpty(groups = SystemDiskGroup.class)
        private String systemDiskRootLocation;

        /**
         * 确保尾巴上有 "/"
         */
        public void setSystemDiskRootLocation(String systemDiskRootLocation) {
            if (!StringUtils.hasText(systemDiskRootLocation.trim())) {
                return;
            }

            systemDiskRootLocation = systemDiskRootLocation.trim();
            if (systemDiskRootLocation.equals("/")) {
                this.systemDiskRootLocation = systemDiskRootLocation;
            } else if (systemDiskRootLocation.endsWith("/")) {
                this.systemDiskRootLocation = systemDiskRootLocation;
            } else {
                this.systemDiskRootLocation = systemDiskRootLocation + "/";
            }
        }

        public interface SystemDiskGroup { }
    }
}
