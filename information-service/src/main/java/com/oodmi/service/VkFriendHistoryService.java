package com.oodmi.service;

import com.oodmi.client.UuidClient;
import com.oodmi.domain.dto.VkFriendHistoryDto;
import com.oodmi.domain.entity.Client;
import com.oodmi.domain.entity.Vk;
import com.oodmi.domain.entity.VkFriend;
import com.oodmi.domain.entity.VkFriendHistory;
import com.oodmi.domain.type.FriendEnum;
import com.oodmi.mapper.VkFriendMapper;
import com.oodmi.repository.VkFriendHistoryRepository;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.friends.responses.GetResponse;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import com.vk.api.sdk.queries.users.UserField;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class VkFriendHistoryService {

    private final UuidClient uuidClient;
    private final VkApiClient vkApiClient;
    private final VkFriendService vkFriendService;
    private final VkFriendHistoryRepository vkFriendHistoryRepository;
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

            VkFriendHistory vkFriendHistory = VkFriendHistory.builder()
                                                             .vk(client.getVk())
                                                             .content(collect)
                                                             .count(count.longValue())
                                                             .uuid(randomUuid)
                                                             .build();
            vkFriendHistoryRepository.save(vkFriendHistory);

            log.info("friendsHistory: {}", vkFriendHistory);
            return randomUuid;
        } catch (ApiException | ClientException e) {
            log.error(Arrays.toString(e.getStackTrace()));
            return "";
        }
    }

    @Transactional
    public List<VkFriend> getFriendsInfo(Client client, List<String> ids) throws ClientException, ApiException {
        List<UserXtrCounters> execute = vkApiClient.users()
                                                   .get(new UserActor(Integer.valueOf(client.getVk().getUserId()),
                                                           client.getVk().getToken()))
                                                   .fields(UserField.PHOTO_100, UserField.DOMAIN)
                                                   .userIds(ids)
                                                   .execute();
        return execute.stream().map(item -> {
            Integer id = item.getId();
            final Optional<VkFriend> friendOptional = vkFriendService.getById(id.toString());
            VkFriend friend;
            if (friendOptional.isPresent()) {
                final VkFriend fri = friendOptional.get();
                if (fri.getTime().plusDays(1L).compareTo(LocalDateTime.now()) < 0) {
                    friend = fri;
                } else {
                    return fri;
                }
            } else {
                friend = new VkFriend();
            }

            friend.setId(item.getId().longValue());
            friend.setDomain(item.getDomain());
            friend.setSurname(item.getLastName());
            friend.setName(item.getFirstName());
            friend.setPhoto(item.getPhoto100());

            vkFriendService.saveOrUpdate(friend);
            return friend;
        }).collect(Collectors.toList());
    }

    @Transactional
    public Map<FriendEnum, List<VkFriend>> getDifference(Client client, String firstUUID, String secondUUID) throws ClientException, ApiException {
        Optional<VkFriendHistory> first = vkFriendHistoryRepository.findByUuid(firstUUID);
        Optional<VkFriendHistory> second = vkFriendHistoryRepository.findByUuid(secondUUID);

        String firstContent = first.map(VkFriendHistory::getContent).orElseThrow(() -> {
            log.error("UUID : {} doesn't exist", firstUUID);
            return new RuntimeException("UUID : " + firstUUID + " doesn't exist");
        });

        String secondContent = second.map(VkFriendHistory::getContent).orElseThrow(() -> {
            log.error("UUID : {} doesn't exist", secondUUID);
            return new RuntimeException("UUID : " + secondUUID + " doesn't exist");
        });

        return difference(client, firstContent, secondContent);
    }

    @Transactional
    public Map<FriendEnum, List<VkFriend>> getDifferenceByTime(Client client, LocalDateTime fromTime, LocalDateTime toTime) throws ClientException, ApiException {
        Optional<VkFriendHistory> from = vkFriendHistoryRepository.findTopByTimeAfterOrderByTimeAsc(fromTime);
        Optional<VkFriendHistory> to = vkFriendHistoryRepository.findTopByTimeBeforeOrderByTimeDesc(toTime);

        String firstContent = from.map(VkFriendHistory::getContent).orElseThrow(() -> {
            log.error("History after time : {} doesn't exist", fromTime);
            return new RuntimeException("History after time : " + fromTime + " doesn't exist");
        });

        String secondContent = to.map(VkFriendHistory::getContent).orElseThrow(() -> {
            log.error("History before time : {} doesn't exist", toTime);
            return new RuntimeException("History before time : " + toTime + " doesn't exist");
        });

        return difference(client, firstContent, secondContent);
    }


    private Map<FriendEnum, List<VkFriend>> difference(Client client, String first, String second) throws ClientException, ApiException {

        Map<FriendEnum, List<VkFriend>> result = new HashMap<>(2);

        String[] firstArray = first.split(",");
        String[] firstArrayCopy = Arrays.copyOf(firstArray, firstArray.length);

        List<String> firstList = new ArrayList<>(Arrays.asList(firstArray)); // a b c
        List<String> secondList = new ArrayList<>(Arrays.asList(second.split(",")));// b c d

        firstList.removeAll(secondList); // a

        secondList.removeAll(Arrays.asList(firstArrayCopy)); //d

        result.put(FriendEnum.REMOVED, getFriendsInfo(client, firstList));
        result.put(FriendEnum.NEW, getFriendsInfo(client, secondList));


        return result;
    }

    @Transactional
    public List<VkFriendHistoryDto> getByLogin(Vk vk) {
        List<VkFriendHistory> vkFriendsByVkHistory = vkFriendHistoryRepository.findVkFriendsByVk(vk);
        return vkFriendsByVkHistory.stream().map(friendMapper::vkFriendToVkFriendDto).collect(Collectors.toList());
    }
}