package com.oodmi.controller;

import com.oodmi.domain.JwtUser;
import com.oodmi.domain.entity.Client;
import com.oodmi.repository.RoleRepository;
import com.oodmi.security.JWTAuthenticationFilter;
import com.oodmi.security.UserDetailsServiceImpl;
import com.oodmi.service.ClientService;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;
import java.security.Principal;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private ClientService clientService;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserDetailsServiceImpl detailsService;
    private AuthenticationManager authenticationManager;

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody Client client) {
        clientService.create(client);
        return ResponseEntity.ok(client);
    }

    @GetMapping(value = "/current")
    public Principal getUser(Principal principal) {
        return principal;
    }

    @GetMapping(value = "/login/vk")
    public String vk(@QueryParam("code") String code, HttpServletResponse res) throws ClientException, ApiException {
        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);
        UserAuthResponse authResponse = vk.oauth()
                .userAuthorizationCodeFlow(6725739, "1DZ4gWbo3XOdOOAerS6q", "http://localhost:4000/uaa/user/login/vk", code)
                .execute();

        UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
        String email = authResponse.getEmail();
        Integer userId = authResponse.getUserId();

        Optional<Client> opt = clientService.findByEmail(email);
        Client client;
        if (!opt.isPresent()) {
            client = new Client()
                    .setEmail(email)
                    .setLogin(userId.toString())
                    .setPassword("password_vk");
            clientService.create(client);
        } else {
            client = opt.get();
        }

        UserDetails credentials = new JwtUser(client.getLogin(), "password_vk",
                Collections.singletonList(new SimpleGrantedAuthority(client.getRole().getName())));

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword(),
                        credentials.getAuthorities()));

        JWTAuthenticationFilter.addHeader(res, authenticate);


        return authResponse.getAccessToken();
    }

}