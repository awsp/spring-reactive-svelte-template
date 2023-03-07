package com.github.awsp.springsveltetemplate.security.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class SecurityContextRepository implements ServerSecurityContextRepository {
  private final AuthenticationManager authenticationManager;
  private static final String BEARER = "Bearer ";

  @Override
  public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
    throw new UnsupportedOperationException("Not supported");
  }

  @Override
  public Mono<SecurityContext> load(ServerWebExchange exchange) {
    return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
        .filter(header -> header.startsWith(BEARER))
        .flatMap(header -> {
          String token = header.replace(BEARER, "");
          Authentication authentication = new UsernamePasswordAuthenticationToken(token, token);
          return authenticationManager.authenticate(authentication).map(SecurityContextImpl::new);
        });
  }
}
