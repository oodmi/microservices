package com.oodmi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/")
public class UuidController {
    @GetMapping("/random/")
    public String getRandomUuid() {
        return UUID.randomUUID().toString();
    }
}
