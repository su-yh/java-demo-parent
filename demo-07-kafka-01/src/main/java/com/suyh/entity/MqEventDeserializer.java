package com.suyh.entity;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * @author 苏雲弘
 * @Description: TODO: 没有起作用，不知道是不是配置不对的原因
 * @date 2020-04-25 13:45
 */
public class MqEventDeserializer implements Deserializer<MQEvent> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public MQEvent deserialize(String topic, byte[] data) {
        return JSON.parseObject(data, MQEvent.class);
    }

    @Override
    public void close() {

    }
}
