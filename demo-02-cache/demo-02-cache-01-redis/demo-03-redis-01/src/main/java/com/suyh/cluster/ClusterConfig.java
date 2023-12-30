package com.suyh.cluster;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class ClusterConfig {

    // 利用属性读取配置文件数据
    @Value("${spring.redis.cluster.nodes}")
    private String nodes;
    @Value("${spring.redis.cluster.nodes}")
    private List<String> nodes2;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private Integer maxIdle;
    @Value("${spring.redis.jedis.pool.min-idle}")
    private Integer minIdle;
//    @Value("${spring.redis.jedis.pool.max-total}")
//    private Integer maxTotal;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private Integer maxWait;

    public GenericObjectPoolConfig getConfig() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(maxIdle);
//        config.setMaxTotal(maxTotal);
        config.setMinIdle(minIdle);
        config.setMaxWaitMillis(maxWait);

        return config;
    }

    @Bean
    public JedisCluster getCluster() {
        Set<HostAndPort> infoSet = new HashSet<>();

        for (String hostAndPort : nodes2) {
            String[] info = hostAndPort.split(":");
            infoSet.add(new HostAndPort(info[0], Integer.parseInt(info[1])));
        }

        JedisCluster cluster = new JedisCluster(infoSet, getConfig());
        return cluster;
    }
}