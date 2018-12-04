package com.oodmi.scheduler;

import com.oodmi.domain.entity.Client;
import com.oodmi.service.ClientService;
import com.oodmi.service.VkFriendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledTask {

    private final VkFriendService vkService;
    private final ClientService clientService;

    @Scheduled(fixedRate = 60000)
    public void task() {
        List<Client> all = clientService.findAll();
        all.forEach(vkService::method);
    }
}
