package com.github.awsp.springsveltetemplate.security.config;

import com.github.awsp.springsveltetemplate.security.service.AuthenticationManager;
import com.github.awsp.springsveltetemplate.security.service.SecurityContextRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Configuration
public class WebSecurityConfig {
  private final AuthenticationManager authenticationManager;
  private final SecurityContextRepository securityContextRepository;

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    return http
        .exceptionHandling()
        .authenticationEntryPoint((webExchange, e) ->
            Mono.fromRunnable(() -> webExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED)))
        .accessDeniedHandler(((exchange, denied) ->
            Mono.fromRunnable(() -> exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN))))
        .and()
        .csrf().disable()
        .formLogin().disable()
        .authenticationManager(authenticationManager)
        .securityContextRepository(securityContextRepository)
        .authorizeExchange()
        .pathMatchers(HttpMethod.OPTIONS).permitAll()
        .pathMatchers("/login").permitAll()
        .anyExchange().authenticated()
        .and().build();
  }
}
