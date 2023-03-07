package com.github.awsp.springsveltetemplate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreType
public abstract class BaseEntity {
  @JsonIgnore
  private boolean deleted = false;

  public abstract Long getId();
}
