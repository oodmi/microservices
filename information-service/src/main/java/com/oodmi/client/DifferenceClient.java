package com.oodmi.client;

import com.oodmi.domain.DifferenceRequest;
import com.oodmi.domain.type.FriendEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient(name = "difference", url = "http://localhost:8202/")
public interface DifferenceClient {
    @GetMapping(value = "api/difference/", consumes = "application/json")
    Map<FriendEnum, List<String>> difference(@RequestBody DifferenceRequest request);
}
