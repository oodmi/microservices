package com.oodmi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "uuid", url = "http://localhost:8000/")
public interface UuidClient {
    @GetMapping(value = "api/guid/", consumes = "application/json")
    String getRandomUuid();
}
