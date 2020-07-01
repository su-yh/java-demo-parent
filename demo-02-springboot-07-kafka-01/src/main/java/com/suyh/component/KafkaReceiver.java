package com.suyh.component;

import com.suyh.constant.KafkaConstant;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.suyh.constant.KafkaConstant.TOPIC_OMS;
import static com.suyh.constant.KafkaConstant.TOPIC_PREFIX;

/**
 * @author 苏雲弘
 * @Description:
 * @date 2020-04-07 14:55
 */
@Component
public class KafkaReceiver {

    @KafkaListener(topics = {TOPIC_PREFIX + KafkaConstant.TOPIC_WMS_ORDER, TOPIC_PREFIX + TOPIC_OMS})
    public void listenKafka(ConsumerRecord<String, String> record) {
        try {
            String topic = record.topic();
            String msg = record.value();
            System.out.println("topic: " + topic + ", CustomEvent.msg: " + msg);
        } catch (Exception ex) {
            System.out.println("kafka消费日志异常-->>" + record.value() + " " + ex.getMessage());
        }
    }

//    @KafkaListener(topics = {TOPIC_PREFIX + KafkaConstant.TOPIC_WMS_ORDER, TOPIC_PREFIX + TOPIC_OMS})
//    public void listenKafka(ConsumerRecord<String, MQEvent> record) {
//        try {
//            String topic = record.topic();
//            MQEvent msg = record.value();
//            System.out.println("topic: " + topic + ", CustomEvent.msg: " + msg);
//        } catch (Exception ex) {
//            System.out.println("kafka消费日志异常-->>" + record.value() + " " + ex.getMessage());
//        }
//    }


}
