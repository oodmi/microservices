package com.oodmi.mapper.impl;

import com.oodmi.domain.dto.StatisticDto;
import com.oodmi.domain.entity.VkFriendHistory;
import com.oodmi.mapper.StatisticMapper;
import org.springframework.stereotype.Component;

@Component
public class StatisticMapperImpl implements StatisticMapper {
    @Override
    public StatisticDto vkFriendHistoryToStatisticDto(VkFriendHistory friend) {
        return StatisticDto.builder().uuid(friend.getUuid())
                           .date(friend.getTime())
                           .amount(friend.getCount())
                           .build();
    }
}
