package com.oodmi.controller;

import com.oodmi.domain.dto.VkFriendHistoryDto;
import com.oodmi.domain.entity.Client;
import com.oodmi.domain.entity.VkFriend;
import com.oodmi.domain.type.FriendEnum;
import com.oodmi.service.ClientService;
import com.oodmi.service.VkFriendHistoryService;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/friend/")
public class VkFriendController {

    private final VkFriendHistoryService vkFriendHistoryService;
    private final ClientService clientService;

    @GetMapping(value = "difference/{login}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getDifference(@PathVariable String login,
                                        @RequestParam("first") String first,
                                        @RequestParam("second") String second
    ) throws ClientException, ApiException {
        final Client client = clientService.findByLogin(login);
        Map<FriendEnum, List<VkFriend>> difference = vkFriendHistoryService.getDifference(client, first, second);
        return ResponseEntity.ok(difference);
    }

    @GetMapping(value = "update/{login}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity update(@PathVariable String login) {
        final Client client = clientService.findByLogin(login);
        final String answer = vkFriendHistoryService.method(client);
        return ResponseEntity.ok().body(answer);
    }

    @GetMapping(value = "/{login}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<VkFriendHistoryDto>> getByLogin(@PathVariable String login) {
        final Client client = clientService.findByLogin(login);
        List<VkFriendHistoryDto> byLogin = vkFriendHistoryService.getByLogin(client.getVk());
        return ResponseEntity.ok().body(byLogin);
    }
}
