package com.suyh3304.client;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

// @JsonTypeInfo 注解的作用，Jackson 多态
// 1. 序列化到时数据库时，增加 @class 属性。
// 2. 反序列化到内存对象时，通过 @class 属性，可以创建出正确的类型
// 可以将接口的实际类型序列化，以及反序列化。
// 接口反序列化，反序列化接口
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public interface FileClientConfig {
}
