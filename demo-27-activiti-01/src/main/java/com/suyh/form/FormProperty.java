package com.suyh.form;

/**
 * 单个控件对应一个表单属性
 */
public interface FormProperty {
    PropertyType getPropertyType();

    /**
     * 是否必须
     * @return boolean
     */
    boolean isRequired();

    /**
     * key 值: 变量名
     * @return key
     */
    String getKey();

    /**
     * 变量值
     * @return value
     */
    Object getValue();

    /**
     * 描述信息
     * @return desc
     */
    String getDescription();
}
