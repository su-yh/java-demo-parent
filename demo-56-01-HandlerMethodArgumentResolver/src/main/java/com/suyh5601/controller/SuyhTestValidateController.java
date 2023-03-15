package com.suyh5601.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

@RestController
@RequestMapping("/suyh")
@Slf4j
public class SuyhTestValidateController {
    @RequestMapping("/validate")
    public Boolean testValidate(@RequestBody @Validated(value = {Create.class, Default.class}) TestReqDto reqDto) {
        return Boolean.TRUE;
    }

    @Data
    public static class TestReqDto {
        private static final long serialVersionUID = 1336211149356488611L;

        @NotBlank(groups = Create.class)
        @Size(max = 128, message = "max size: {max}", groups = Create.class)
        private String appId;
        @NotBlank(groups = Update.class)
        @Size(max = 64, message = "max size: {max}", groups = Update.class)
        private String subAppId;

        @NotBlank
        @Size(max = 64, message = "max size: {max}")
        private String tenantId;

        // @Valid 级联约束
        @NotNull(message = "environ 不能是null")
        @Valid
        private TempEnviron environ;
    }

    @Data
    public static class TempEnviron {
        @NotBlank
        @Size(max = 32, message = "max size: {max}")
        private String deployEnv;
        @NotBlank
        @Size(max = 32, message = "max size: {max}")
        private String clusterType;

        @NotBlank
        @Size(max = 32, message = "max size: {max}")
        private String region;

        @NotBlank
        @Size(max = 32, message = "max size: {max}")
        private String platform;
    }

    public interface Create {
    }

    public interface Update {
    }
}
