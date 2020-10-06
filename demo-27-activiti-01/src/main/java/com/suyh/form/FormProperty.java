package com.suyh.form;

/**
 * 单个控件对应一个表单属性
 */
public interface FormProperty {
    FormType getFormType();

    /**
     * 是否必须
     * @return boolean
     */
    boolean isRequired();

    /**
     * key 值
     * @return key
     */
    String getKey();

    Object getValue();

    /**
     * 描述信息
     * @return
     */
    String getDescription();
}
