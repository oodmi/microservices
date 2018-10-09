package com.oodmi.controller;

import com.oodmi.domain.entity.Client;
import com.oodmi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
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
        clientService.create(client);
    }

    @GetMapping(value = "/current")
    public Principal getUser(Principal principal) {
        return principal;
    }

    @RequestMapping("/")
    public String hello() {
        return "hello world";
    }
}