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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/friend/")
public class VkFriendController {

    private final VkFriendHistoryService vkFriendHistoryService;
    private final ClientService clientService;
    private final AuthClient authClient;
    private final HttpServletRequest req;

    @GetMapping(value = "difference/uuid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getDifference(@RequestParam("first") String first,
                                        @RequestParam("second") String second) throws ApiException {
        HashMap<String, Object> user = authClient.getUser(req.getHeader("Authorization"));
        final Client client = clientService.findByLogin((String) user.get("name"));
        Map<FriendEnum, List<VkFriend>> difference = vkFriendHistoryService.getDifference(client, first, second);
        return ResponseEntity.ok(difference);
    }

    @GetMapping(value = "difference/time", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getDifferenceByTime(@RequestParam("from")
                                              @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
                                                      LocalDateTime from,
                                              @RequestParam("to")
                                              @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
                                                      LocalDateTime to) throws ApiException {
        HashMap<String, Object> user = authClient.getUser(req.getHeader("Authorization"));
        final Client client = clientService.findByLogin((String) user.get("name"));
        Map<FriendEnum, List<VkFriend>> difference = vkFriendHistoryService.getDifferenceByTime(client, from, to);
        return ResponseEntity.ok(difference);
    }

    @GetMapping(value = "update")
    public ResponseEntity update() {
        HashMap<String, Object> user = authClient.getUser(req.getHeader("Authorization"));
        final Client client = clientService.findByLogin((String) user.get("name"));
        final String answer = vkFriendHistoryService.method(client);
        return ResponseEntity.ok().body(answer);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<VkFriendHistoryDto>> getAllByLogin() {
        HashMap<String, Object> user = authClient.getUser(req.getHeader("Authorization"));
        final Client client = clientService.findByLogin((String) user.get("name"));
        List<VkFriendHistoryDto> byLogin = vkFriendHistoryService.getByLogin(client.getVk());
        return ResponseEntity.ok().body(byLogin);
    }

    @GetMapping(value = "/uuid/{uuid}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<VkFriend>> getAllByLoginAndUuid(@PathVariable String uuid) {
        HashMap<String, Object> user = authClient.getUser(req.getHeader("Authorization"));
        final Client client = clientService.findByLogin((String) user.get("name"));
        List<VkFriend> friends = vkFriendHistoryService.getFriendsByLoginAndUuid(client, uuid);
        return ResponseEntity.ok().body(friends);
    }

    @GetMapping(value = "/history/count", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Long> getCountByLogin() {
        HashMap<String, Object> user = authClient.getUser(req.getHeader("Authorization"));
        final Client client = clientService.findByLogin((String) user.get("name"));
        Long count = vkFriendHistoryService.getCountByLogin(client.getVk());
        return ResponseEntity.ok().body(count);
    }

    @GetMapping(value = "/history/page/{page}/size/{size}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<VkFriendHistoryDto>> paginationGetByLogin(@PathVariable("page") Integer page,
                                                                         @PathVariable("size") Integer size) {
        HashMap<String, Object> user = authClient.getUser(req.getHeader("Authorization"));
        final Client client = clientService.findByLogin((String) user.get("name"));
        List<VkFriendHistoryDto> byLogin = vkFriendHistoryService.getByLogin(client.getVk(), page, size);
        return ResponseEntity.ok().body(byLogin);
    }
}
