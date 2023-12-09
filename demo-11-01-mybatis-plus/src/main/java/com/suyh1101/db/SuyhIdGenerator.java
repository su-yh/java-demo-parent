package com.suyh1101.db;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.toolkit.Sequence;

/**
 * @author suyh
 * @since 2023-12-09
 */
public class SuyhIdGenerator implements IdentifierGenerator {
    private final Sequence sequence;

    public SuyhIdGenerator() {
        this.sequence = new Sequence(null);
    }

    @Override
    public Number nextId(Object entity) {
        return sequence.nextId();
    }
}
