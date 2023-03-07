package com.github.awsp.springsveltetemplate.security.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class RefreshToken {
  private Long id;
  private String map;
  private String token;
  private Instant expiration;
}
