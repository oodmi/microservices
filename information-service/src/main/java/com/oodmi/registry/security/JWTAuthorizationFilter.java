package com.oodmi.registry.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.oodmi.registry.security.SecurityConstants.*;


public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws IOException {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            DecodedJWT verify = JWT.require(HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""));
            String user = verify.getSubject();
            Claim roles = verify.getClaim("roles");
            List<String> grantedAuthorities = roles.asList(String.class);
            List<SimpleGrantedAuthority> collect = grantedAuthorities.stream()
                    .filter(Objects::nonNull)
                    .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, collect);
            }
            return null;
        }
        return null;
    }

}