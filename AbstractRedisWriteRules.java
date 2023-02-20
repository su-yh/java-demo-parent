@Slf4j
public abstract class AbstractRedisWriteRules<R, T extends ArmsRuleWrapperVo<R>>
        extends AbstractRedisTemplate<T> {
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    @Value("${spring.profiles.active}")
    private String curEnv;

    public AbstractRedisWriteRules(Class<T> javaType, RedisConnectionFactory factory, RedisProperties redisProperties) {
        super(javaType, factory, redisProperties);
    }

    @NonNull
    protected abstract ArmsRuleType ruleType();

    protected abstract T instanceWrapperVo();

    public void doWriteRule(@NonNull UniqueAppKeyVo appKeyVo, @Nullable R rule, long timestamp) {
        final String appName = appKeyVo.getApp();
        final Long dashboardEnvId = appKeyVo.getDashboardEnvId();
        final String key = buildRedisKey(appName, dashboardEnvId);
        final String ruleName = ruleType().name();
        // 有效时间[24, 25) 小时
        final int randomSeconds = SECURE_RANDOM.nextInt(3600);
        long timeout = 24 * 3600L + randomSeconds;

        T wrapperVo = instanceWrapperVo();
        wrapperVo.setTimestamp(timestamp);
        wrapperVo.setConfigValue(rule);

        // 将最新的规则数据写到redis 缓存
        super.opsForValue().set(key, wrapperVo, timeout, TimeUnit.SECONDS);

        ChannelTopic topic = ChannelTopic.of("/" + curEnv + "/" + cleanAppName(appName) +
                "/" + dashboardEnvId + "/" + ruleName);

        log.debug("redis write rule, app name: {}, dashboard env id: {}, rule name: {}, key: {}, topic: {}",
                appName, dashboardEnvId, ruleName, key, topic.getTopic());

        RedisQueueRuleMsgVo msg = new RedisQueueRuleMsgVo();
        msg.setAppKeyVo(appKeyVo).setRuleType(ruleType());

        // 推送规则的新增、更新、删除等操作通知到redis，由arwen 监听处理。
        super.convertAndSend(topic.getTopic(), msg);
    }

    // 读写必须一致
    private String buildRedisKey(@NonNull String appName, long dashboardEnvId) {
        return String.join(":", curEnv, appName.replaceAll(":", "_"), dashboardEnvId + "", ruleType().name());
    }

    private String cleanAppName(@NonNull String appName) {
        return appName.replaceAll("/", "_");
    }
}
