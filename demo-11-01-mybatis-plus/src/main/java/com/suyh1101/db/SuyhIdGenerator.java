package com.suyh1101.db;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.toolkit.Sequence;
import org.springframework.stereotype.Component;

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
    // 另外一个就是最好是一毫秒内，可生成的数尽量大一些。
    // 它在同一毫秒内，达到最大值后，会等到下一毫秒，再重新生成。
    private final Sequence sequence;

    public SuyhIdGenerator() {
        this.sequence = new Sequence(null);
    }

    @Override
    public Number nextId(Object entity) {
        return sequence.nextId();
    }

    @Override
    public String nextUUID(Object entity) {
        return IdentifierGenerator.super.nextUUID(entity);
    }
}
