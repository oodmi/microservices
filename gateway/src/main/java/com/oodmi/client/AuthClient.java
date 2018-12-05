package com.oodmi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("auth-service")
@RequestMapping("/uaa/user")
public interface AuthClient {
    @GetMapping(value = "/login/vk", consumes = "application/json")
    String vk(@RequestParam("code") String code, @RequestParam("url") String url);
}
