package com.oodmi.controller;

import com.oodmi.domain.dto.VkFriendDto;
import com.oodmi.domain.entity.Client;
import com.oodmi.domain.type.FriendEnum;
import com.oodmi.service.ClientService;
import com.oodmi.service.VkFriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/friend/")
public class VkFriendController {

    private final VkFriendService vkFriendService;
    private final ClientService clientService;

    @GetMapping("difference/")
    public ResponseEntity getDifference(@RequestParam("first") String first, @RequestParam("second") String second) {
        Map<FriendEnum, List<String>> difference = vkFriendService.getDifference(first, second);
        return ResponseEntity.ok(difference);
    }

    @GetMapping("update/{login}")
    public ResponseEntity update(@PathVariable String login) {
        final Client client = clientService.findByLogin(login);
        final String answer = vkFriendService.method(client);
        return ResponseEntity.ok().body(answer);
    }

    @GetMapping(value = "/{login}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<VkFriendDto>> getByLogin(@PathVariable String login) {
        final Client client = clientService.findByLogin(login);
        List<VkFriendDto> byLogin = vkFriendService.getByLogin(client.getVk());
        return ResponseEntity.ok().body(byLogin);
    }
}
