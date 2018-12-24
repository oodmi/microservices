package com.oodmi.repository;

import com.oodmi.domain.entity.Vk;
import com.oodmi.domain.entity.VkFriendHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VkFriendHistoryRepository extends JpaRepository<VkFriendHistory, Long> {
    Optional<VkFriendHistory> findByUuid(String userId);

    Optional<VkFriendHistory> findTopByTimeAfterOrderByTimeAsc(LocalDateTime localDateTime);

    Optional<VkFriendHistory> findTopByTimeBeforeOrderByTimeDesc(LocalDateTime localDateTime);

    List<VkFriendHistory> findVkFriendsByVk(Vk vk);
}
