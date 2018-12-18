package com.oodmi.mapper.impl;

import com.oodmi.domain.dto.VkFriendHistoryDto;
import com.oodmi.domain.entity.VkFriendHistory;
import com.oodmi.mapper.VkFriendMapper;
import org.springframework.stereotype.Component;

@Component
public class VkFriendMapperImpl implements VkFriendMapper {
    @Override
    public VkFriendHistoryDto vkFriendToVkFriendDto(VkFriendHistory friend) {
        return VkFriendHistoryDto.builder().uuid(friend.getUuid())
                                 .content(friend.getContent())
                                 .time(friend.getTime())
                                 .count(friend.getCount())
                                 .build();
    }
}
