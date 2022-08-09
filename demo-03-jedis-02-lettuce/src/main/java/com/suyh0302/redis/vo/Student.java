package com.suyh0302.redis.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.redis.listener.ChannelTopic;

import java.io.Serializable;

@Accessors(chain = true)
@Data
public class Student implements Serializable {
    private static final long serialVersionUID = 4076831502342333319L;

    private static ChannelTopic topic = new ChannelTopic("/redis/publish");

    public static ChannelTopic getTopic() {
        return topic;
    }

    private String id;
    private String name;
    private int grade;
}
