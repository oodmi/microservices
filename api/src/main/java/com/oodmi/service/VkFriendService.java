package com.oodmi.service;

import com.oodmi.domain.entity.VkFriend;
import com.oodmi.repository.VkFriendRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class VkFriendService {

    private final VkFriendRepository repository;

    public Optional<VkFriend> getById(String id) {
        return repository.getById(Long.valueOf(id));
    }

    @Transactional
    public void saveOrUpdate(VkFriend friend) {
        repository.save(friend);
    }
}
