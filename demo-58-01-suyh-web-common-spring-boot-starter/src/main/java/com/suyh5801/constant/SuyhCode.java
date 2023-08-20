package com.suyh5801.constant;

public enum SuyhCode {
    OK(0, "操作成功"),
    ERROR(-1, "系统异常"),
    FAIL(-2, "服务错误"),
    NOT_IMPLEMENTED(-3, "暂未实现！！！"),
    ILLEGAL_PARAMETER(-4, "参数不合法"),
    MISSING_PARAMETER(-5, "缺少必要的参数"),
    PARSE_USER_TOKEN_FAILED(-6, "用户token 解析失败"),
    USER_INFO_PROPERTY_MISSING(-7, "用户信息缺少某些数据"),

    LOGIN_REGISTRY_USER_EXISTS(100001, "用户名已存在"),
    EMAIL_SEND_FAILED(100002, "邮件发送失败"),
    PHONE_MESSAGE_SEND_FAILED(100003, "短信发送失败"),
    EMAIL_LOGIN_CHECK_FAILED(100004, "邮箱验证码错误失效或者过期"),
    EMAIL_ACCOUNT_REGISTRY_FAILED(100005, "email 已经注册"),
    PHONE_LOGIN_CHECK_FAILED(100006, "手机验证码错误失效或者过期"),
    USER_NOT_LOGIN(100007, "用户未登录"),
    USER_LOGIN_EXPIRE(100008, "登录过期"),
    NO_LOGIN_USER(100009, "用户未登录或者登录已过期"),
    LOGIN_PHONE_USER_NOT_FOUND(100011, "没有找到手机用户信息"),
    LOGIN_EMAIL_USER_NOT_FOUND(100012, "没有找到email用户信息"),
    CAPTCHA_TEXT_EXPIRE(100013, "验证码过期"),
    CAPTCHA_TEXT_ERROR(100014, "验证码错误"),
    CAPTCHA_UUID_LOST(100015, "缺少验证码uuid"),
    CAPTCHA_TEXT_LOST(100016, "缺少验证码文本"),
    USER_NO_PHONE(100017, "用户没有绑定手机"),
    USER_NO_EMAIL(100018, "用户没有绑定email"),
    INVITATION_CODE_ERROR(100019, "邀请码无效或者错误"),
    USER_PHONE_EXISTS(100020, "该手机号已被注册"),
    USER_EMAIL_EXISTS(100021, "该邮箱已被注册"),
    MODEL_VERSION_NOT_EXISTS(100022, "对应版本模型不存在"),
    MODEL_VERSION_NOT_OWNER(100023, "对应版本模型属主错误"),


    ;

    private final boolean success;
    private final String desc;

    public boolean isSuccess() {
        return success;
    }

    public String getDesc() {
        return desc;
    }

    SuyhCode(Integer code, String msg) {
        this.success = (code == 0);
        this.desc = msg;
    }

}