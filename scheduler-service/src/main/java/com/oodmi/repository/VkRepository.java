package com.oodmi.repository;

import com.oodmi.domain.entity.Vk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VkRepository extends JpaRepository<Vk, Long> {
    Optional<Vk> findByUserId(String userId);
}
