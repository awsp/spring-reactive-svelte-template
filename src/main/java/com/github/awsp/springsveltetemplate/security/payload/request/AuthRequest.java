package com.github.awsp.springsveltetemplate.security.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
  @JsonProperty(required = true)
  private String username;
  @JsonProperty(required = true)
  private String password;
}
