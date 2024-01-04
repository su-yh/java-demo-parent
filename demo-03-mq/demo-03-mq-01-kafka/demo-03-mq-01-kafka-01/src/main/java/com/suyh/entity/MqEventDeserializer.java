package com.suyh.entity;

import com.suyh.utils.JsonUtil;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
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
        String jsonData = new String(data, StandardCharsets.UTF_8);
        return JsonUtil.deserialize(jsonData, MQEvent.class);
    }

    @Override
    public void close() {

    }
}
