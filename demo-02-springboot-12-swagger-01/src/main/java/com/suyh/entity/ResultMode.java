package com.suyh.entity;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 苏雲弘
 * @Description:
 * @date 2020-04-03 17:09
 */
@ApiModel(description = "返回实体")
public class ResultMode<T> implements Serializable {

    private static final long serialVersionUID = 2168115660376016205L;

    /**
     * 执行返回的实体,可以为空.
     */
    private List<T> model;

    /**
     * 本次查询异常信息
     */
    private String errMsg = "";

    /**
     * 本次查询异常编码
     */
    private int errCode = -1;

    /**
     * 构造函数
     */
    public ResultMode() {
        model = new ArrayList<>();
    }

    /**
     * 构造函数，返回False的方法
     *
     * @param errCode 异常编码
     * @param errMsg  异常信息
     */
    public ResultMode(int errCode, String errMsg) {
        model = new ArrayList<>();
        this.errMsg = errMsg;
        this.errCode = errCode;
    }

    /**
     * 设置错误消息
     *
     * @param errCode 异常编码
     * @param errMsg  异常信息
     * @param model   执行返回的实体,可以为空
     */
    public ResultMode(int errCode, String errMsg, List<T> model) {
        this.model = model;
        this.errMsg = errMsg;
        this.errCode = errCode;
    }

    /**
     * 构造函数，返回True的方法
     *
     * @param model 执行返回的实体,可以为空
     */
    public ResultMode(List<T> model) {
        this.model = model;
    }

    /**
     * 构造函数，返回True的方法
     *
     * @param model 执行返回的实体,可以为空.
     */
    public ResultMode(T model) {
        this.model = new ArrayList<>();
        this.getModel().add(model);
    }

    /**
     * 追加一个结果实体数据
     * @param model 返回实体数据
     */
    public void addModel(T model) {
        this.model.add(model);
    }

    public List<T> getModel() {
        return model;
    }

    public void setModel(List<T> model) {
        this.model = model;
    }


    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }
}

