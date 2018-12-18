package com.oodmi.repository;

import com.oodmi.domain.entity.Vk;
import com.oodmi.domain.entity.VkFriendHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VkFriendHistoryRepository extends JpaRepository<VkFriendHistory, Long> {
    Optional<VkFriendHistory> findByUuid(String userId);
    List<VkFriendHistory> findVkFriendsByVk(Vk vk);
}
