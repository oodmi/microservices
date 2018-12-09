package com.oodmi.service;

import com.oodmi.client.UuidClient;
import com.oodmi.domain.dto.VkFriendDto;
import com.oodmi.domain.entity.Client;
import com.oodmi.domain.entity.Vk;
import com.oodmi.domain.entity.VkFriend;
import com.oodmi.domain.type.FriendEnum;
import com.oodmi.mapper.VkFriendMapper;
import com.oodmi.repository.VkFriendRepository;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.friends.responses.GetResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class VkFriendService {

    private final UuidClient uuidClient;
    private final VkApiClient vkApiClient;
    private final VkFriendRepository vkFriendRepository;
    private final VkFriendMapper friendMapper;


    @Transactional
    public String method(Client client) {
        try {
            if (client.getVk() == null) {
                log.info("VK is null");
                return "";
            }
            GetResponse execute = vkApiClient.friends().get(new UserActor(Integer.valueOf(client.getVk().getUserId()), client.getVk().getToken()))
                                             .execute();
            Integer count = execute.getCount();
            String collect = execute.getItems().stream().map(Object::toString).collect(Collectors.joining(","));
            String randomUuid = uuidClient.getRandomUuid();

            VkFriend vkFriend = VkFriend.builder()
                                        .vk(client.getVk())
                                        .content(collect)
                                        .count(count.longValue())
                                        .uuid(randomUuid)
                                        .build();
            vkFriendRepository.save(vkFriend);

            log.info("friends: {}", vkFriend);
            return randomUuid;
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Transactional
    public Map<FriendEnum, List<String>> getDifference(String firstUUID, String secondUUID) {
        Optional<VkFriend> first = vkFriendRepository.findByUuid(firstUUID);
        Optional<VkFriend> second = vkFriendRepository.findByUuid(secondUUID);

        String firstContent = first.map(VkFriend::getContent).orElseThrow(() -> {
            log.error("UUID : {} doesn't exist", firstUUID);
            return new RuntimeException("UUID : " + firstUUID + " doesn't exist");
        });

        String secondContent = second.map(VkFriend::getContent).orElseThrow(() -> {
            log.error("UUID : {} doesn't exist", secondUUID);
            return new RuntimeException("UUID : " + secondUUID + " doesn't exist");
        });

        return difference(firstContent, secondContent);
    }


    private Map<FriendEnum, List<String>> difference(String first, String second) {

        Map<FriendEnum, List<String>> result = new HashMap<>(2);

        String[] firstArray = first.split(",");
        String[] firstArrayCopy = Arrays.copyOf(firstArray, firstArray.length);

        List<String> firstList = new ArrayList<>(Arrays.asList(firstArray)); // a b c
        List<String> secondList = new ArrayList<>(Arrays.asList(second.split(",")));// b c d

        firstList.removeAll(secondList); // a

        secondList.removeAll(Arrays.asList(firstArrayCopy)); //d

        result.put(FriendEnum.REMOVED, firstList);
        result.put(FriendEnum.NEW, secondList);

        return result;
    }

    public List<VkFriendDto> getByLogin(Vk vk) {
        List<VkFriend> vkFriendsByVk = vkFriendRepository.findVkFriendsByVk(vk);
        return vkFriendsByVk.stream().map(friendMapper::vkFriendToVkFriendDto).collect(Collectors.toList());
    }
}
