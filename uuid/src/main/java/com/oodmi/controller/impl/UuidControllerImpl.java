package com.oodmi.controller.impl;

import com.oodmi.controller.UuidController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UuidControllerImpl implements UuidController {
    @Override
    @GetMapping("/random/")
    public String getRandomUuid() {
        return null;
    }
}
