package com.oodmi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("uuid")
@RequestMapping("/uuid")
public interface UuidClient {
    @GetMapping(value = "/random/", consumes = "application/json")
    String getRandomUuid();
}
