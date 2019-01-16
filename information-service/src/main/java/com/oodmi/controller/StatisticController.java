package com.oodmi.controller;

import com.oodmi.client.AuthClient;
import com.oodmi.domain.dto.StatisticDto;
import com.oodmi.domain.entity.Client;
import com.oodmi.service.ClientService;
import com.oodmi.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/statistic/")
public class StatisticController {

    private final StatisticService statisticService;
    private final ClientService clientService;
    private final AuthClient authClient;
    private final HttpServletRequest req;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<StatisticDto>> getAllByLogin() {
        HashMap<String, Object> user = authClient.getUser(req.getHeader("Authorization"));
        final Client client = clientService.findByLogin((String) user.get("name"));
        List<StatisticDto> byLogin = statisticService.getStatistic(client.getVk());
        return ResponseEntity.ok().body(byLogin);
    }
}
