package com.ankaboot.AuthenticationServer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/user").permitAll()
        .antMatchers("/create-user").hasRole("ADMIN")
        .antMatchers("/update-user").hasRole("ADMIN")
        .antMatchers("/delete-user").hasRole("ADMIN")
        .anyRequest()
        .authenticated();
  }
}