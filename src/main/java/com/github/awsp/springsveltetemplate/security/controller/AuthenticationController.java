package com.github.awsp.springsveltetemplate.security.controller;

import com.github.awsp.springsveltetemplate.security.payload.request.AuthRequest;
import com.github.awsp.springsveltetemplate.security.payload.response.AuthResponse;
import com.github.awsp.springsveltetemplate.security.service.UserService;
import com.github.awsp.springsveltetemplate.security.utils.JwtUtil;
import com.github.awsp.springsveltetemplate.security.utils.PBKDF2Encoder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
  private final UserService userService;
  private final PBKDF2Encoder encoder;
  private final JwtUtil jwtUtil;

  @PostMapping("/login")
  public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest request) {
    return userService.findByUsername(request.getUsername())
        .filter(user -> encoder.encode(request.getPassword()).equals(user.getPassword()))
        .map(user -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(user))))
        .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
  }
}
