package com.suyh1101.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author suyh
 * @since 2023-12-11
 */
public class UpdateSequenceEvent extends ApplicationEvent {
    @Getter
    private final long instanceId;

    public UpdateSequenceEvent(long instanceId) {
        super(instanceId);

        this.instanceId = instanceId;
    }
}
