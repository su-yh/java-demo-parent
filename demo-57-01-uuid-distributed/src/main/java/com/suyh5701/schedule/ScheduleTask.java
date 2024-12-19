package com.suyh5701.schedule;

import com.suyh5701.component.uuid.v2.UuidComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class ScheduleTask {
    private final UuidComponent uuidComponent;

    @Scheduled(initialDelay = 1, fixedRate = 3600, timeUnit = TimeUnit.SECONDS)
    public void serviceSequenceTask() {
        uuidComponent.serviceSequenceTask();
    }
}
