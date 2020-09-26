package test.com.suyh;

import com.suyh.Kafka03Application;
import com.suyh.constant.KafkaConstant;
import com.suyh.entity.WmsCkOmsShipmentMO;
import com.suyh.entity.MQEvent;
import com.suyh.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.UUID;

import static com.suyh.constant.KafkaConstant.EVENT_WMS_SHIPMENT_OUT;
import static com.suyh.constant.KafkaConstant.TOPIC_PREFIX;


/**
 * @author 苏雲弘
 * @Description:
 * @date 2020-04-24 18:34
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = Kafka03Application.class)
@Slf4j
public class KafkaTest {

    private static final String topicWmsOrder = TOPIC_PREFIX + KafkaConstant.TOPIC_WMS_ORDER;
    private static final String topicOms = TOPIC_PREFIX + KafkaConstant.TOPIC_OMS;

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Resource
    private KafkaTemplate<String, MQEvent> kafkaTemplateMqEvent;

    @Test
    public void testKafkaTemplate() {
        WmsCkOmsShipmentMO data = makeKafkaDataByWmsOut();
        MQEvent<WmsCkOmsShipmentMO> mqEvent = new MQEvent<>(
                UUID.randomUUID().toString(), EVENT_WMS_SHIPMENT_OUT, data);
        ListenableFuture<SendResult<String, String>> future
                = kafkaTemplate.send(topicWmsOrder, JsonUtil.serializable(mqEvent));
        System.out.println("kafkaTemplate send data to topic: " + topicWmsOrder
                + ", event id: " + mqEvent.getEventId());
        future.addCallback(sendResult -> {
            if (sendResult == null) {
                log.info("sendResult is null");
                return;
            }

            // SendResult<String, String> res = sendResult;
            log.info("sendResult: {}", sendResult);
        }, exception -> {
            log.error("kafka send message failed.", exception);
        });

        mqEvent.setEventId(UUID.randomUUID().toString());
        kafkaTemplate.send(topicOms, JsonUtil.serializable(mqEvent));
        System.out.println("kafkaTemplate send data to topic: " + topicOms
                + ", event id: " + mqEvent.getEventId());
    }

    @Test
    public void testKafkaTemplateMqEvent() {
        // TODO: 未能成功。这个暂时不能用。可能是配置的问题，需要序列化什么的。
        WmsCkOmsShipmentMO data = makeKafkaDataByWmsOut();
        MQEvent<WmsCkOmsShipmentMO> mqEvent = new MQEvent<>(
                UUID.randomUUID().toString(), EVENT_WMS_SHIPMENT_OUT, data);
        kafkaTemplateMqEvent.send(topicWmsOrder, mqEvent);
        System.out.println("kafkaTemplate send data to topic: " + topicWmsOrder
                + ", event id: " + mqEvent.getEventId());

        mqEvent.setEventId(UUID.randomUUID().toString());
        kafkaTemplateMqEvent.send(topicOms, mqEvent);
        System.out.println("kafkaTemplate send data to topic: " + topicOms
                + ", event id: " + mqEvent.getEventId());
    }

    private WmsCkOmsShipmentMO makeKafkaDataByWmsOut() {

        WmsCkOmsShipmentMO shipment = new WmsCkOmsShipmentMO();
        shipment.setOrderNo("suyh-OrderNo");
        shipment.setOrderType("suyh-OrderType");
        shipment.setPriority("urgent");


        return shipment;
    }
}
