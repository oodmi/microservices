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
    public void create(Client client) {

        Optional<Client> existing = repository.findByLogin(client.getLogin());
        existing.ifPresent(it -> {
            throw new IllegalArgumentException("client already exists: " + it.getLogin());
        });

        String hash = encoder.encode(client.getPassword());
        client.setPassword(hash);
        client.setRole(roleRepository.findByName("USER").orElseThrow(() -> new IllegalArgumentException("role was nor found")));

        repository.save(client);

        log.info("new client has been created: {}", client.getLogin());
    }

    @Transactional
    public void update(Client client) {
        repository.save(client);
        log.info("client has been updated: {}", client.getLogin());
    }

    @Transactional
    public Optional<Client> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}