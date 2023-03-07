package com.github.awsp.springsveltetemplate.security.utils;

import com.github.awsp.springsveltetemplate.security.Role;
import com.github.awsp.springsveltetemplate.security.domain.model._private.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtUtil {
  @Value("${app.jjwt.secret}")
  private String secret;

  @Value("${app.jjwt.expiration}")
  private String expirationTime;

  private Key key;

  @PostConstruct
  public void init() {
    this.key = Keys.hmacShaKeyFor(secret.getBytes());
  }

  public Claims getAllClaimsFromToken(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJwt(token)
        .getBody();
  }

  public String getUsernameFromToken(String token) {
    return getAllClaimsFromToken(token).getSubject();
  }

  public Date getExpirationDateFromToken(String token) {
    return getAllClaimsFromToken(token).getExpiration();
  }

  public boolean isTokenExpired(String token) {
    Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  public String generateToken(User user) {
    Map<String, List<Role>> claims = new HashMap<>();
    claims.put("role", user.getRoles());
    return doGenerateToken(claims, user.getUsername());
  }

  public boolean validateToken(String token) {
    return !isTokenExpired(token);
  }

  private String doGenerateToken(Map<String, List<Role>> claims, String username) {
    long expirationTimeInSeconds = Long.parseLong(expirationTime);
    Date createdDate = new Date();
    Date expirationDate = new Date(createdDate.getTime() + expirationTimeInSeconds * 1000);

    return Jwts.builder()
        .setClaims(claims)
        .setSubject(username)
        .setIssuedAt(createdDate)
        .setExpiration(expirationDate)
        .signWith(key)
        .compact();
  }
}
