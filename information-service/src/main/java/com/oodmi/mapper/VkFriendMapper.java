package com.oodmi.mapper;

import com.oodmi.domain.dto.VkFriendHistoryDto;
import com.oodmi.domain.entity.VkFriendHistory;

public interface VkFriendMapper {

    VkFriendHistoryDto vkFriendHistoryToVkFriendHistoryDto(VkFriendHistory friend);
}
