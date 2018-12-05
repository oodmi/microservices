package com.oodmi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("information-service")
@RequestMapping("/information")
public interface InformationClient {

    @GetMapping(value = "update/{login}", consumes = "application/json")
    ResponseEntity update(@PathVariable("login") String login);
}
