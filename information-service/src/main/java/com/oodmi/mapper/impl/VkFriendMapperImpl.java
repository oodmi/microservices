package com.oodmi.mapper.impl;

import com.oodmi.domain.dto.VkFriendDto;
import com.oodmi.domain.entity.VkFriend;
import com.oodmi.mapper.VkFriendMapper;
import org.springframework.stereotype.Component;

@Component
public class VkFriendMapperImpl implements VkFriendMapper {
    @Override
    public VkFriendDto vkFriendToVkFriendDto(VkFriend friend) {
        return VkFriendDto.builder().uuid(friend.getUuid())
                          .content(friend.getContent())
                          .time(friend.getTime())
                          .count(friend.getCount())
                          .build();
    }
}
