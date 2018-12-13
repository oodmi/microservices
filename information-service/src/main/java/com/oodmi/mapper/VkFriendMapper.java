package com.oodmi.mapper;

import com.oodmi.domain.dto.VkFriendDto;
import com.oodmi.domain.entity.VkFriend;

public interface VkFriendMapper {

    VkFriendDto vkFriendToVkFriendDto(VkFriend friend);
}
