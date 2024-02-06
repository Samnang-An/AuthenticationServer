package com.ankaboot.AuthenticationServer;

import com.ankaboot.AuthenticationServer.dataaccess.service.UserService;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableAuthorizationServer
public class AuthenticationServerApplication implements CommandLineRunner {

  @Autowired
  private UserService userService;

  public static void main(String[] args) {
    SpringApplication.run(AuthenticationServerApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    try {
      userService.fetchUser("admin");
    } catch (UsernameNotFoundException unf) {
      userService.createUser("admin", "123", List.of("ADMIN"));
      userService.createUser("maintainer", "123", List.of("MAINTAINER"));
      userService.createUser("analyst", "123", List.of("ANALYST"));
    }
  }
}
