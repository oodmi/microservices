package com.oodmi.security;

import com.oodmi.domain.JwtUser;
import com.oodmi.domain.entity.Client;
import com.oodmi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new JwtUser(client.getLogin(), client.getPassword(), emptyList());
    }
}