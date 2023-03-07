package com.github.awsp.springsveltetemplate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Author {
  private String firstName;
  private String lastName;
  @JsonProperty(required = true)
  private String email;
}
