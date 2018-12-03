package com.oodmi.controller;

import com.oodmi.service.VkFriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class VkFriendController {

    private final VkFriendService vkFriendService;

    @GetMapping("difference/")
    public ResponseEntity getDifference(@RequestParam("first") String first, @RequestParam("second") String second) {
        Map<VkFriendService.Type, List<String>> difference = vkFriendService.getDifference(first, second);
        return ResponseEntity.ok(difference);
    }
}
