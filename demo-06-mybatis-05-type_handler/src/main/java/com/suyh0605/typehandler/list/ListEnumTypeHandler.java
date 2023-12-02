package com.suyh0605.typehandler.list;

import com.suyh0605.enums.StatusEnum;

/**
 * @author suyh
 * @since 2023-12-02
 */
public class ListEnumTypeHandler extends ListTypeHandler<StatusEnum> {
    public ListEnumTypeHandler() {
        super(StatusEnum.class);
    }
}
