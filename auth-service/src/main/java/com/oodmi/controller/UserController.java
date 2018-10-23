package com.oodmi.controller;

import com.oodmi.domain.entity.Client;
import com.oodmi.service.ClientService;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.QueryParam;
import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    private ClientService clientService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RestTemplate restTemplate;

    @Autowired
    public UserController(ClientService clientService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.clientService = clientService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody Client client) {
        clientService.create(client);
    }

    @GetMapping(value = "/current")
    public Principal getUser(Principal principal) {
        return principal;
    }

    @GetMapping(value = "/vk")
    public String vk(@QueryParam("token") String token, @QueryParam("user_id") String userId) {
        return token;
    }

    @GetMapping(value = "/code")
    public String vk(@QueryParam("code") String code) throws ClientException, ApiException {
        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);
        UserAuthResponse authResponse = vk.oauth()
                .userAuthorizationCodeFlow(6725739, "1DZ4gWbo3XOdOOAerS6q", "http://localhost:4000/uaa/user/code", code)
                .execute();

        UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
        return authResponse.getAccessToken();
    }

    @RequestMapping("/")
    public String hello() {
        return "hello world";
    }
}