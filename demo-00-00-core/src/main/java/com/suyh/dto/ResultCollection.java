package com.suyh.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class ResultCollection<T> extends ResultEntity {

    public ResultCollection(Collection<T> data) {
        this.data = data;
    }

    private Collection<T> data;
}
