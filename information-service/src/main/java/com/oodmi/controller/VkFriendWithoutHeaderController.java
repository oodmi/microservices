package com.oodmi.controller;

import com.oodmi.client.AuthClient;
import com.oodmi.domain.dto.VkFriendHistoryDto;
import com.oodmi.domain.entity.Client;
import com.oodmi.domain.entity.VkFriend;
import com.oodmi.domain.type.FriendEnum;
import com.oodmi.service.ClientService;
import com.oodmi.service.VkFriendHistoryService;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/friend/")
public class VkFriendWithoutHeaderController {

    private final VkFriendHistoryService vkFriendHistoryService;
    private final ClientService clientService;
    private final AuthClient authClient;
    private final HttpServletRequest req;

    @GetMapping(value = "difference/uuid/{login}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getDifference(@PathVariable String login,
                                        @RequestParam("first") String first,
                                        @RequestParam("second") String second) throws ApiException {
        final Client client = clientService.findByLogin(login);
        Map<FriendEnum, List<VkFriend>> difference = vkFriendHistoryService.getDifference(client, first, second);
        return ResponseEntity.ok(difference);
    }

    @GetMapping(value = "difference/time/{login}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getDifferenceByTime(@PathVariable String login,
                                              @RequestParam("from")
                                              @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
                                                      LocalDateTime from,
                                              @RequestParam("to")
                                              @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
                                                      LocalDateTime to) throws ApiException {
        final Client client = clientService.findByLogin(login);
        Map<FriendEnum, List<VkFriend>> difference = vkFriendHistoryService.getDifferenceByTime(client, from, to);
        return ResponseEntity.ok(difference);
    }

    @GetMapping(value = "update/{login}")
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
