package com.oodmi.service;

import com.oodmi.domain.entity.Client;
import com.oodmi.repository.ClientRepository;
import com.oodmi.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ClientRepository repository;
    private final RoleRepository roleRepository;

    @Autowired
    public ClientService(ClientRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

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
}
