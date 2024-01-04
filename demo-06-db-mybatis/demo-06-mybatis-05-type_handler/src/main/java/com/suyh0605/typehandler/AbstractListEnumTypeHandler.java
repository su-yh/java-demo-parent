package com.suyh0605.typehandler;

import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.suyh0605.util.JsonUtil;

import java.util.List;

/**
 * @author suyh
 * @since 2023-12-02
 */
public abstract class AbstractListEnumTypeHandler<E> extends AbstractJsonTypeHandler<List<E>> {
    private final Class<E> type;
    public AbstractListEnumTypeHandler(Class<E> type) {
        this.type = type;
    }

    @Override
    protected List<E> parse(String json) {
        return JsonUtil.deserializeToList02(json, type, JsonUtil.DB_OBJECT_MAPPER);
    }

    @Override
    protected String toJson(List<E> obj) {
        return JsonUtil.serializable(obj, JsonUtil.DB_OBJECT_MAPPER);
    }
}
