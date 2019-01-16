package com.oodmi.service;

import com.oodmi.domain.dto.StatisticDto;
import com.oodmi.domain.entity.Vk;
import com.oodmi.domain.entity.VkFriendHistory;
import com.oodmi.mapper.StatisticMapper;
import com.oodmi.repository.VkFriendHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticService {

    private final VkFriendHistoryRepository vkFriendHistoryRepository;
    private final StatisticMapper statisticMapper;

    @Transactional
    public List<StatisticDto> getStatistic(Vk vk) {
        List<VkFriendHistory> vkFriendsByVkHistory = vkFriendHistoryRepository.findVkFriendsByVk(vk);
        return vkFriendsByVkHistory.stream().map(statisticMapper::vkFriendHistoryToStatisticDto)
                                   .collect(Collectors.toList());
    }
}
