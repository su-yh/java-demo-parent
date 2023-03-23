package com.suyh5701.component.util;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.suyh5701.mapper.UuidMapper;
import com.suyh5701.util.GlobalRandom;
import com.suyh5701.util.UuidUtils;
import com.suyh5701.vo.UuidVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

@Component
@RequiredArgsConstructor
@Slf4j
public class UuidComponent implements InitializingBean {
    // 1 秒内最多允许产生的UUID 数量，超过则会重复。
    private static final int MAX_LOCAL_SEQUENCE = 0XFFFFFF;
    private final UuidMapper uuidMapper;

    @Value("${spring.application.name}")
    private String serviceName;

    // 服务序列号：高8 bits 用来自增存放同一微服务的实例数上限，8 bits 最大是256，但是去除0 和256 还有254 个
    private final AtomicInteger serviceSequence = new AtomicInteger();

    // 本地序列号： 24 bits 自增序列数
    private int localSequence = 0;
    private final ReentrantLock localSequenceLock = new ReentrantLock();

    // 某个秒级时间戳的使用计数
    private final Cache<Integer, AtomicLong> cacheSecondsCount = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.SECONDS).initialCapacity(4).maximumSize(60).build();

    @Override
    public void afterPropertiesSet() throws Exception {
        UuidUtils.setUuidComponent(this);

        // 初始化两个随机值，以免在去数据库拿之前就有人来调用。
        serviceSequence.set(GlobalRandom.RANDOM.nextInt());

        try {
            localSequenceLock.lock();
            localSequence = GlobalRandom.RANDOM.nextInt(MAX_LOCAL_SEQUENCE);
        } finally {
            localSequenceLock.unlock();
        }
    }

    /**
     * 应用启动成功，多数据库初始化一个当前应用实例的序列号
     * 同时定时调度任务，以更新serviceSequence 的值
     */
    @Transactional
    public void serviceSequenceTask() {
        UuidVo uuidVo = uuidMapper.selectByPrimaryKey(serviceName);
        if (uuidVo == null) {
            throw new RuntimeException("m_uuid_t cannot found service: " + serviceName);
        }

        Integer sequenceNumber = uuidVo.getSequenceNumber();
        if (sequenceNumber == null) {
            throw new RuntimeException("m_uuid_t sequence_number is null for " + serviceName);
        }

        if (sequenceNumber >= 0XFF) {
            sequenceNumber = 1;
        }
        serviceSequence.set(sequenceNumber);

        uuidMapper.updateByPrimaryKey(serviceName, sequenceNumber + 1);

        log.info("service name: {}, current serviceSequence: {}", serviceName, Integer.toHexString(serviceSequence.get()));
    }

    /**
     * 0xFFFFFFFF00000000: UTC 时间对应的秒值
     * 0x00000000FFFFFF00: 本地序列自增
     * 0x00000000000000FF: 微服务集群实例序列号(集群实例数量最大不要超过127 个最好，考虑K8S 先启动后停的情况)
     *
     * @return 分布式唯一ID
     */
    public long generateUuidLong() {
        int epochSecond = validCount();
        int localNumber = nextLocalSequence() << 8;
        int lower = serviceSequence.get() | localNumber;
        final ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putInt(epochSecond);
        buffer.putInt(lower);
        buffer.flip();
        return buffer.getLong();
    }

    public String generateUuidString() {
        long mostSigBits = generateUuidLong();
        long leastSigBits = GlobalRandom.RANDOM.nextLong();
        UUID uuid = new UUID(mostSigBits, leastSigBits);
        return uuid.toString();
    }

    private int nextLocalSequence() {
        try {
            localSequenceLock.lock();
            int nextNumber = ++localSequence;
            if (nextNumber >= MAX_LOCAL_SEQUENCE) {
                nextNumber = 0;
                localSequence = 0;
            }
            return nextNumber;
        } finally {
            localSequenceLock.unlock();
        }
    }

    /**
     * 限制一秒内生成的uuid 的数量。
     * 当前本地计数是有效的，则返回当前计数时间的秒值。
     * 每一秒最多产生{@link #MAX_LOCAL_SEQUENCE}个的计数，超过则需要等到下一秒才允许再次生成。
     * 返回的就是当前允许生成uuid的有效UTC 秒值。
     */
    private int validCount() {
        int epochSecond;
        while (true) {
            Instant instant = Instant.now();
            epochSecond = (int) instant.getEpochSecond();
            AtomicLong secondCount = cacheSecondsCount.get(epochSecond, (key) -> new AtomicLong(0));
            assert secondCount != null;
            long currentNumber = secondCount.incrementAndGet();
            if (currentNumber < MAX_LOCAL_SEQUENCE) {
                break;
            }

            if (currentNumber <= MAX_LOCAL_SEQUENCE) {
                log.info("invalid count current seconds: {}, current number: {}", Long.toHexString(epochSecond), Long.toHexString(currentNumber));
            }
            secondCount.set(MAX_LOCAL_SEQUENCE);
            Thread.yield();
        }

        return epochSecond;
    }
}
