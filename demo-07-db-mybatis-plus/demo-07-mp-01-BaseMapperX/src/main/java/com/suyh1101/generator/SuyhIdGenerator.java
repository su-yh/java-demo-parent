package com.suyh1101.generator;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.suyh1101.event.UpdateSequenceEvent;
import com.suyh1101.sequence.Sequence;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 这里将自定义的id 生成器定义为一个bean 对象，并交由spring 管理之后。
 * mybatis plus 将会取此作为id 生成器来使用。
 *
 * @author suyh
 * @since 2023-12-09
 */
@Component
public class SuyhIdGenerator implements IdentifierGenerator {
    // 最好是每隔一段时间，就重新生成。
    // 在1 毫秒内最大生成的值是2 ^ 12
    // 它在同一毫秒内，达到最大值后，会等到下一毫秒，再重新生成。
    private Sequence sequence;
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public SuyhIdGenerator() {
        // 程序启动的时候就先来一个随机值吧，这个影响不大，不要秋考虑得太极端了。
        // TODO: suyh - 初始化也应该使用数据库的数据。
        this.sequence = new Sequence();
    }

    @Override
    public Number nextId(Object entity) {
        return nextId();
    }

    public long nextId() {
        Lock readLock = rwLock.readLock();
        try {
            readLock.lock();
            return sequence.nextId();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public String nextUUID(Object entity) {
        // TODO: suyh - 这个UUID 怎么生成呢？干脆直接将Number 值转换成16 进程字符串得了
        long number = nextId();
        return Long.toHexString(number);
    }

    // TODO: suyh -
    // 每隔一个小时去数据库查一下，来更新一下这个Sequence，然后在程序启动的时候也从数据库查询一个来初始化。
    // 这个就放到一个runner 里面去处理实现就好了。
    @EventListener(UpdateSequenceEvent.class)
    public void updateSequence(UpdateSequenceEvent event) {
        long instanceId = event.getInstanceId();

        Lock writeLock = rwLock.writeLock();
        try {
            writeLock.lock();
            this.sequence = new Sequence(instanceId);
        } finally {
            writeLock.unlock();
        }
    }
}
