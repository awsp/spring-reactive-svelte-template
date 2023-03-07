package com.github.awsp.springsveltetemplate.security.service;

import com.github.awsp.springsveltetemplate.security.Role;
import com.github.awsp.springsveltetemplate.security.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {
  private final JwtUtil jwtUtil;

  @Override
  public Mono<Authentication> authenticate(Authentication authentication) {
    String token = authentication.getCredentials().toString();
    String username = jwtUtil.getUsernameFromToken(token);

    return Mono.just(jwtUtil.validateToken(token))
        .filter(valid -> valid)
        .switchIfEmpty(Mono.empty())
        .map(valid -> {
          Claims claims = jwtUtil.getAllClaimsFromToken(token);
          List<Role> roles = claims.get("role", List.class);
          return new UsernamePasswordAuthenticationToken(username, null, roles.stream()
              .map(a -> new SimpleGrantedAuthority(a.name())).collect(Collectors.toList()));
        });

  }
}
