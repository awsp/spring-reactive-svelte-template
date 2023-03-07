package com.github.awsp.springsveltetemplate.security.service;

import com.github.awsp.springsveltetemplate.security.domain.model._private.User;
import com.github.awsp.springsveltetemplate.security.utils.JwtUtil;
import com.github.awsp.springsveltetemplate.security.utils.PBKDF2Encoder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.awsp.springsveltetemplate.security.Role.ROLE_ADMIN;
import static com.github.awsp.springsveltetemplate.security.Role.ROLE_USER;

@Service
@RequiredArgsConstructor
public class UserService {
  private final PBKDF2Encoder encoder;

  // TODO: Switch to using R2DBC
  private final Map<String, User> repo = new HashMap<>();

  @PostConstruct
  public void init() {
    User user = new User();
    user.setRoles(List.of(ROLE_USER));
    user.setUsername("user");
    user.setPassword(encoder.encode("password"));
    user.setEnabled(true);

    User admin = new User();
    admin.setRoles(List.of(ROLE_ADMIN));
    admin.setUsername("admin");
    admin.setPassword(encoder.encode("password"));
    user.setEnabled(true);

    repo.put("user", user);
    repo.put("admin", admin);
  }

  public Mono<User> findByUsername(String username) {
    return Mono.justOrEmpty(repo.get(username));
  }
}
