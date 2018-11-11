package com.oodmi.controller;

import com.oodmi.domain.JwtUser;
import com.oodmi.domain.entity.Client;
import com.oodmi.domain.entity.Vk;
import com.oodmi.resource.ClientResource;
import com.oodmi.security.JWTAuthenticationFilter;
import com.oodmi.service.ClientService;
import com.oodmi.service.VkService;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Collections;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class ClientController implements ClientResource {

    private ClientService clientService;
    private AuthenticationManager authenticationManager;
    private VkService vkService;
    private HttpServletRequest req;
    private HttpServletResponse res;

    @Override
    public ResponseEntity signUp(@RequestBody Client client) {
        clientService.create(client);
        return ResponseEntity.ok(client);
    }

    @Override
    public Principal getUser(Principal principal) {
        return principal;
    }

    @Override
    public String vk(String code, String url) throws Exception {
        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);
        UserAuthResponse authResponse = vk.oauth()
                                          .userAuthorizationCodeFlow(
                                                  6725739,
                                                  "1DZ4gWbo3XOdOOAerS6q",
                                                  url,
                                                  code).execute();

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

        Vk token = new Vk()
                .setUserId(actor.getId().toString())
                .setToken(actor.getAccessToken());

        vkService.create(token);

        client.setVk(token);
        clientService.update(client);

        UserDetails credentials = new JwtUser(
                client.getLogin(),
                "password_vk",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + client.getRole().getName().toUpperCase())));

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword(),
                        credentials.getAuthorities()));

        return JWTAuthenticationFilter.addHeader(res, authenticate);
    }

}