package com.suyh.entity;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * @author 苏雲弘
 * @Description: TODO: 没有起作用，不知道是不是配置不对的原因
 * @date 2020-04-25 13:43
 */
public class MqEventSerializer implements Serializer<MQEvent> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String topic, MQEvent mqEvent) {
        return JSON.toJSONBytes(mqEvent);
    }

    @Override
    public void close() {

    }
}
