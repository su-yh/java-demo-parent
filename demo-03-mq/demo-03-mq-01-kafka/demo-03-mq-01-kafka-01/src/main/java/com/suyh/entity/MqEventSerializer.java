package com.suyh.entity;

import com.suyh.utils.JsonUtil;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;
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
        String jsonEvent = JsonUtil.serializable(mqEvent);
        return jsonEvent.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public void close() {

    }
}
