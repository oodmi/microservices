package com.oodmi.registry.controller;

import com.oodmi.registry.client.AuthClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.oodmi.security.SecurityConstants.HEADER_STRING;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final AuthClient authClient;
    private final HttpServletRequest req;
    private final HttpServletResponse res;

    @GetMapping(value = "")
    public String index(@RequestParam(value = "code", required = false) String code) throws Exception {

        if (code != null) {
            log.info("vk login");
            String token = authClient.vk(code, req.getRequestURL().toString().substring(0, req.getRequestURL().toString().length() - 1));
            res.addHeader(HEADER_STRING, token);

        }
        return "main/index";
    }
}