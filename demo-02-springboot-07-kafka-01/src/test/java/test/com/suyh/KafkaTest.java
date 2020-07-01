package test.com.suyh;

import com.alibaba.fastjson.JSON;
import com.suyh.Kafka03Application;
import com.suyh.constant.KafkaConstant;
import com.suyh.entity.WmsCkOmsShipmentMO;
import com.suyh.entity.MQEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

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
        kafkaTemplate.send(topicWmsOrder, JSON.toJSONString(mqEvent));
        System.out.println("kafkaTemplate send data to topic: " + topicWmsOrder
                + ", event id: " + mqEvent.getEventId());

        mqEvent.setEventId(UUID.randomUUID().toString());
        kafkaTemplate.send(topicOms, JSON.toJSONString(mqEvent));
        System.out.println("kafkaTemplate send data to topic: " + topicOms
                + ", event id: " + mqEvent.getEventId());
    }

    @Test
    public void testKafkaTemplateMqEvent() {
        // TODO: 未能成功。这个暂时不能用
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
