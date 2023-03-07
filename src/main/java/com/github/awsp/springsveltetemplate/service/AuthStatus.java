package com.github.awsp.springsveltetemplate.service;

import com.github.awsp.springsveltetemplate.annotations.GenerateTypescript;

@GenerateTypescript
public enum AuthStatus {
  LOGIN,
  UNAUTHENTICATED,
  LOGOUT
}
