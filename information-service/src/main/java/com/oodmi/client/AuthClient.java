package com.oodmi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@FeignClient("auth-service")
@RequestMapping("/uaa/user")
public interface AuthClient {
    @GetMapping(value = "/current")
    HashMap<String, Object> getUser(@RequestHeader("Authorization") String authorization);
}
