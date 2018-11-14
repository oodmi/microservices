package com.oodmi.registry.controller;

import static com.oodmi.security.SecurityConstants.HEADER_STRING;

import com.oodmi.registry.client.AuthClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final AuthClient authClient;
    private final HttpServletRequest req;
    private final HttpServletResponse res;

    @GetMapping(value = "")
    public String index(Model model, @RequestParam(value = "code", required = false) String code) throws Exception {
        if (code != null) {
            log.info("vk login");
            String token = authClient.vk(code, req.getRequestURL().toString().substring(0, req.getRequestURL().toString().length() - 1));
            res.addHeader(HEADER_STRING, token);
            model.addAttribute("token", token);
        } else {
            model.addAttribute("token", "");
        }
        return "main/index";
    }
}