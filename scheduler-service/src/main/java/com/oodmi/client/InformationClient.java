package com.oodmi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("information-service")
public interface InformationClient {

    @GetMapping(value = "friend/update/{login}", consumes = "application/json")
    ResponseEntity update(@PathVariable("login") String login);
}
