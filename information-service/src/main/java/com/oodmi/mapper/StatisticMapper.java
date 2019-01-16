package com.oodmi.mapper;

import com.oodmi.domain.dto.StatisticDto;
import com.oodmi.domain.dto.VkFriendHistoryDto;
import com.oodmi.domain.entity.VkFriendHistory;

public interface StatisticMapper {

    StatisticDto vkFriendHistoryToStatisticDto(VkFriendHistory friend);
}
