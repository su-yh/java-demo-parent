@RestController
@RequestMapping("/suyh")
@Slf4j
public class SuyhTestValidateController {
    @RequestMapping("/validate")
    public Boolean testValidate(@RequestBody @Validated(value = {Create.class, Default.class}) @Valid TestReqDto reqDto) {
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

        /**
         * 在蓝版对应的就是企业ID
         */
        @NotBlank
        @Size(max = 64, message = "max size: {max}")
        private String tenantId;

        @Valid
        private TempEnviron environ;
    }

    @Data
    public static class TempEnviron {
        /**
         * 部署环境，如：dev sit pro。
         * 优先使用spring.profiles.active 的值，
         * 若没有则取docker_env，
         * 再没有则取default。
         */
        @NotBlank
        @Size(max = 32, message = "max size: {max}")
        private String deployEnv;
        @NotBlank
        @Size(max = 32, message = "max size: {max}")
        private String clusterType;

        /**
         * 部署区域：贵阳、东莞东等，his 平台取docker_region 配置值
         */
        @NotBlank
        @Size(max = 32, message = "max size: {max}")
        private String region;

        /**
         * 平台：his、华为云(hw_cloud)。
         */
        @NotBlank
        @Size(max = 32, message = "max size: {max}")
        private String platform;
    }

    public interface Create {
    }

    public interface Update {
    }
}
