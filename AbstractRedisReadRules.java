@Slf4j
public abstract class AbstractRedisReadRules<T> extends AbstractRedisTemplate<T> {
    @Value("${spring.profiles.active}")
    private String curEnv;

    public AbstractRedisReadRules(Class<T> javaType, RedisConnectionFactory factory, RedisProperties redisProperties) {
        super(javaType, factory, redisProperties);
    }

    /**
     * 规则名称，需要与写入key 的完全匹配。
     */
    @NonNull
    protected abstract ArmsRuleType ruleType();

    public T readRule(@NonNull String appName, long dashboardEnvId) {
        final String key = buildRedisKey(appName, dashboardEnvId);
        return super.opsForValue().get(key);
    }

    // 读写必须一致
    private String buildRedisKey(@NonNull String appName, long dashboardEnvId) {
        return String.join(":", curEnv, appName.replaceAll(":", "_"), dashboardEnvId + "", ruleType().name());
    }
}
