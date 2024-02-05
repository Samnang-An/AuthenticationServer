package com.ankaboot.AuthenticationServer;

import com.ankaboot.AuthenticationServer.dataaccess.service.UserService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableResourceServer
@EnableAuthorizationServer
public class AuthenticationServerApplication implements CommandLineRunner {

  @Autowired
  private UserService userService;

  public static void main(String[] args) {
    SpringApplication.run(AuthenticationServerApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
//    userService.createUser("admin","123",Arrays.asList("ADMIN"));
//    userService.createUser("manager","123",Arrays.asList("MANAGER"));
//    userService.createUser("analyst","123",Arrays.asList("ANALYST"));
  }
}
