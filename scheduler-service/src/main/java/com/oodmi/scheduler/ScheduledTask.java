package com.oodmi.scheduler;

import com.oodmi.client.UuidClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledTask {

    private final UuidClient uuidClient;

    @Scheduled(fixedRate = 5000)
    public void task() {
        String randomUuid = uuidClient.getRandomUuid();
        log.info(randomUuid);
        log.info("hi!");
    }
}
