package com.github.awsp.springsveltetemplate.security.domain.repo;

import com.github.awsp.springsveltetemplate.security.domain.model._private.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
  Mono<User> findFirstByUsername(String username);
}
