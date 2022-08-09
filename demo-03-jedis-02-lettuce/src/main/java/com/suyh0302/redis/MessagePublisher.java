package com.suyh0302.redis;

import com.suyh0302.redis.vo.Student;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.lang.NonNull;

public interface MessagePublisher {
    void publish(@NonNull ChannelTopic topic, @NonNull Student message);
}
