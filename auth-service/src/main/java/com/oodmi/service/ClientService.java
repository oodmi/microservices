package com.oodmi.service;

import com.oodmi.domain.entity.Client;
import com.oodmi.repository.ClientRepository;
import com.oodmi.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ClientService {

    private final BCryptPasswordEncoder encoder;
    private final ClientRepository repository;
    private final RoleRepository roleRepository;

    @Transactional
    public void create(Client user) {

        Optional<Client> existing = repository.findByLogin(user.getLogin());
        existing.ifPresent(it -> {
            throw new IllegalArgumentException("user already exists: " + it.getLogin());
        });

        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setRole(roleRepository.findByName("USER").orElseThrow(() -> new IllegalArgumentException("role was nor found")));

        repository.save(user);

        log.info("new user has been created: {}", user.getLogin());
    }

    @Transactional
    public Optional<Client> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
