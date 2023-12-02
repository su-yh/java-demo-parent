package com.suyh0605.typehandler.jackson;

import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.suyh0605.enums.StatusEnum;
import com.suyh0605.util.JsonUtil;

import java.util.List;

/**
 * @author suyh
 * @since 2023-12-02
 */
public class ListEnumTypeHandler extends AbstractJsonTypeHandler<List<StatusEnum>> {
    @Override
    protected List<StatusEnum> parse(String json) {
        return JsonUtil.deserializeToList02(json, StatusEnum.class);
    }

    @Override
    protected String toJson(List<StatusEnum> obj) {
        return JsonUtil.serializable(obj);
    }
}
