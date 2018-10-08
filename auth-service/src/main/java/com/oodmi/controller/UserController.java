package com.oodmi.controller;

import com.oodmi.domain.entity.Client;
import com.oodmi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private ClientService clientService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(ClientService clientService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.clientService = clientService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody Client client) {
//        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        clientService.create(client);
    }

    @RequestMapping("/")
    public String hello() {
        return "hello world";
    }
}