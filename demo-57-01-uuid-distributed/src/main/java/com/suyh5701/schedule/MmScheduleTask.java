package com.suyh5701.schedule;

import com.suyh5701.component.util.MmUuidComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class MmScheduleTask {
    private final MmUuidComponent uuidComponent;

    @Scheduled(initialDelay = 1, fixedRate = 3600, timeUnit = TimeUnit.SECONDS)
    public void serviceSequenceTask() {
        uuidComponent.serviceSequenceTask();
    }
}
