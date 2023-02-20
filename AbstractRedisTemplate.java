@Slf4j
public abstract class AbstractRedisTemplate<T> extends RedisTemplate<String, T> {
    private final RedisConnectionFactory factory;
    private final Jackson2JsonRedisSerializer<T> serializer;
    private final RedisProperties redisProperties;

    public AbstractRedisTemplate(Class<T> javaType, RedisConnectionFactory factory, RedisProperties redisProperties) {
        this.factory = factory;
        serializer = new Jackson2JsonRedisSerializer<>(javaType);
        this.redisProperties = redisProperties;
    }

    @Override
    public void afterPropertiesSet() {
        ObjectMapper mapper = new ObjectMapper();
        JsonUtils.mapperConfig(mapper);
        serializer.setObjectMapper(mapper);

        final StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        super.setKeySerializer(stringRedisSerializer);
        super.setHashKeySerializer(stringRedisSerializer);
        super.setValueSerializer(serializer);
        super.setHashValueSerializer(serializer);
        super.setConnectionFactory(factory);
        super.afterPropertiesSet();
    }

    public T deserialize(@Nullable byte[] bytes) {
        return serializer.deserialize(bytes);
    }

    @Override
    public Set<String> keys(@NonNull String pattern) {
        if (redisProperties.getClientType().equals(RedisProperties.ClientType.LETTUCE)) {
            Set<String> keys = new HashSet<>();
            RedisConnection connection = factory.getConnection();
            ScanOptions scanOptions = ScanOptions.scanOptions().match(pattern).count(1000L).build();
            Cursor<byte[]> cursor = connection.scan(scanOptions);
            while (cursor.hasNext()) {
                keys.add(new String(cursor.next()));
            }

            return keys;
        }

        return super.keys(pattern);
    }
}
