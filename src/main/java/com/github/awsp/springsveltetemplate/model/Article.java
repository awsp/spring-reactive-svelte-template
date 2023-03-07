package com.github.awsp.springsveltetemplate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Article extends BaseEntity {
  private Long id;
  @JsonProperty(required = true)
  private String title;
  private String body;
  private Author author;
}
