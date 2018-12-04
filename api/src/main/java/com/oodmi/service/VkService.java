package com.oodmi.service;

import com.oodmi.domain.entity.Vk;
import com.oodmi.repository.VkRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class VkService {
    private final VkRepository repository;

    @Transactional
    public void create(Vk vk) {

        Optional<Vk> existing = repository.findByUserId(vk.getUserId());

        if (existing.isPresent()) {
            Vk ex = existing.get();
            ex.setToken(vk.getToken());
            repository.save(ex);
            vk.setId(ex.getId());
            repository.flush();
            log.info("vk has been updated: {}", vk.getUserId());
        } else {
            repository.save(vk);
            log.info("vk has been created: {}", vk.getUserId());
        }
    }
}
