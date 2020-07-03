package com.suyh.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分页查询实体类
 * <p>
 * 创建时间 2019/4/3 11:09:54
 */
@Data
@NoArgsConstructor
public class PageInfo<T> implements Serializable {
    /**
     * 建议序列化的class都给一个序列化的ID，这样可以保证序列化的成功，版本的兼容性。
     */
    private static final long serialVersionUID = 100000L;

    /**
     * 查询过滤条件
     */
    private T filterModel;

    /**
     * 当前页 以1开始为第一页
     */
    private int currentPage = 1;

    /**
     * 每页长
     */
    private int pageLength = 10;

    /**
     * 排序方向 asc: 升序 , desc: 降序
     *
     * TODO: 排序字段要怎么给
     */
    private String order = "asc";

    /**
     * 查询当前页及页长
     *
     * @param currentPage 当前页
     * @param pageLength  每页多少条
     */
    public PageInfo(int currentPage, int pageLength) {
        this.currentPage = currentPage;
        this.pageLength = pageLength;
    }

    /**
     * 查询当前页及页长
     *
     * @param filterModel 查询条件实体内容
     * @param currentPage 当前页
     * @param pageLength  每页多少条
     */
    public PageInfo(T filterModel, int currentPage, int pageLength) {
        this.filterModel = filterModel;
        this.currentPage = currentPage;
        this.pageLength = pageLength;
    }


}
