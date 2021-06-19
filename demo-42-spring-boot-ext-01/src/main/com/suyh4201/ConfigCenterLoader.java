package com.suyh4201;

/**
 * 环境后置处理器
 * 用于处理系统变量与环境变量加载之后，在配置属性文件加载之前处理配置中心的配置属性值加载。
 * 注意：在该流程位置日志还没有初始化，所以还无法使用日志系统打印日志。
 * 不过可以通过抛异常的形式，将错误信息包装在异常消息中。
 *
 * @since 2021-06-18
 */
public class ConfigCenterLoader implements EnvironmentPostProcessor, Ordered {

    /**
     * 配置中心属性名，用于存放在系统属性中。
     */
    public static final String CONFIG_CENTER_NAME = "configCenter";

    private ConfigurableEnvironment environment;

    private WebClient configCenterClient;

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        this.environment = environment;
        // 是否禁止连接到配置中心
        Boolean enabledConfigCenter = environment.getProperty(
                SentinelConfigConstants.ENABLED_CONFIG_CENTER, Boolean.class, true);
        if (!enabledConfigCenter) {
            return;
        }

        init();

        Map<String, Object> configCenterProperties = buildMap();
        MutablePropertySources sources = environment.getPropertySources();
        if (sources.contains(CONFIG_CENTER_NAME)) {
            throw new RuntimeException("duplicate config property source name " + CONFIG_CENTER_NAME);
        }
        // 这里是添加到后面，现在前面应该是有系统变量，环境变量，命令行参数。
        sources.addLast(new MapPropertySource(CONFIG_CENTER_NAME, configCenterProperties));
    }

    private Map<String, Object> buildMap() {
        Map<String, Object> map = new HashMap<>();
        // TODO: 这里添加自己的配置属性。
        return map;
    }

    private void init() {

        String configCenterRestUrl = configCenterUrl.replace("/services/ConfigCenterService",
                "/services/saasConfigcenterGetConfig");

        configCenterClient = WebClient.create(configCenterRestUrl);
    }

    private ConfigCenterPropertiesVo loadConfigCenterProperties(String basicToken) throws JsonProcessingException {
        Mono<String> stringMono = configCenterClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("application_id", appId)
                        .queryParam("sub_application_id", subAppId)
                        .queryParam("region", dockerRegion)
                        .queryParam("environment", dockerEnv)
                        .queryParam("version", dockerVersion)
                        .build())
                .header(HttpHeaders.AUTHORIZATION, basicToken)
                .retrieve()
                .onStatus(httpStatus -> !httpStatus.equals(HttpStatus.OK),
                        clientResponse -> Mono.error(new JalorSentinelException("http request failed")))
                .bodyToMono(String.class);
        String result = stringMono.block();
        System.out.println("result: " + result);
        return JsonUtils.deserialize(result, ConfigCenterPropertiesVo.class);
    }

    @Override
    public int getOrder() {
        // 在配置文件加载之前，这个也很重要，它指定了要在应用配置文件加载之前执行。
        return ConfigFileApplicationListener.DEFAULT_ORDER - 2;
    }
}
