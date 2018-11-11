package com.oodmi.resource;

import com.oodmi.domain.entity.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/user")
public interface ClientResource {

    @PostMapping("/sign-up")
    ResponseEntity signUp(@RequestBody Client client);

    @GetMapping(value = "/current")
    Principal getUser(Principal principal);

    @GetMapping(value = "/login/vk")
    String vk(@RequestParam("code") String code,
              @RequestParam("url") String urls) throws Exception;

}