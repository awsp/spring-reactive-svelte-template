package com.github.awsp.springsveltetemplate;

import com.github.awsp.springsveltetemplate.security.Role;
import com.github.awsp.springsveltetemplate.security.domain.model._private.User;
import com.github.awsp.springsveltetemplate.security.domain.repo.UserRepository;
import com.github.awsp.springsveltetemplate.security.utils.PBKDF2Encoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.r2dbc.core.DatabaseClient;

import java.util.List;

@SpringBootApplication
public class SpringSvelteTemplateApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringSvelteTemplateApplication.class, args);
  }

  @Bean
  ApplicationListener<ApplicationReadyEvent> onReady(DatabaseClient dbc,
                                                     PBKDF2Encoder encoder,
                                                     UserRepository userRepository) {
    String ddl = "create table if not exists user(id int primary key, username varchar(255), password varchar(512)";

    return event -> {
      User user = new User();
      user.setUsername("root");
      user.setPassword(encoder.encode("password"));
      user.setRoles(List.of(Role.ROLE_ADMIN));

      userRepository.save(user);
    };
  }

}
