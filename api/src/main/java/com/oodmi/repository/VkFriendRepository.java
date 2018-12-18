package com.oodmi.repository;

import com.oodmi.domain.entity.VkFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VkFriendRepository extends JpaRepository<VkFriend, Long> {
    Optional<VkFriend> getById(Long id);
}
