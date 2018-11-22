package com.oodmi.scheduler;

import com.oodmi.client.UuidClient;
import com.oodmi.domain.entity.Client;
import com.oodmi.repository.ClientRepository;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.friends.responses.GetResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledTask {

    private final UuidClient uuidClient;

    private final VkApiClient vkApiClient;

    private final ClientRepository clientRepository;

    @Scheduled(fixedRate = 10000)
    public void task() {
        List<Client> all = clientRepository.findAll();
        all.forEach(this::method);

        String randomUuid = uuidClient.getRandomUuid();
        log.info(randomUuid);
        log.info("hi!");
    }

    public void method(Client client) {
        try {
            if(client.getVk() == null){
                log.info("VK is null");
                return;
            }
            GetResponse execute = vkApiClient.friends().get(new UserActor(Integer.valueOf(client.getVk().getUserId()), client.getVk().getToken()))
                                             .execute();
            Integer count = execute.getCount();
            String collect = execute.getItems().stream().map(Object::toString).collect(Collectors.joining(","));
                log.info("count: {}, friends: {}", count, collect);
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

    }
}
