package com.oodmi.scheduler;

import com.oodmi.client.InformationClient;
import com.oodmi.domain.entity.Client;
import com.oodmi.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ScheduledTask {

    private final InformationClient client;
    private final ClientService clientService;

    @Scheduled(fixedRate = 3600 * 1000)
    public void task() {
        List<Client> all = clientService.findAll();

        all.forEach(item -> client.update(item.getLogin()));
    }
}
