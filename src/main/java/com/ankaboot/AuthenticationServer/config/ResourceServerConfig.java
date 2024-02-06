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
//    http.csrf().disable().authorizeRequests().anyRequest().authenticated();
    http
        .authorizeRequests()
        .antMatchers("/user").hasAuthority("ADMIN")
        .antMatchers("/create_user").hasAuthority("ADMIN")
        .antMatchers("/update_user/**").hasAuthority("ADMIN")
        .antMatchers("/delete_user/**").hasAuthority("ADMIN")
        .anyRequest()
        .authenticated();
  }
}
