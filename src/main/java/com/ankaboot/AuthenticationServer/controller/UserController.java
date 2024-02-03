package com.ankaboot.AuthenticationServer.controller;

import com.ankaboot.AuthenticationServer.dataaccess.service.UserService;
import com.ankaboot.AuthenticationServer.dto.UserDto;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/create-user")
  public ResponseEntity<UserDto> createAccount(@RequestBody UserForm user) {
    return ResponseEntity.ok().body(userService.createUser(
        user.getUsername(),
        user.getPassword(),
        user.getRoles()
    ));
  }

  @RequestMapping(value = {"/user"}, produces = "application/json")
  public Map<String, Object> user(OAuth2Authentication user) {
    Map<String, Object> userInfo = new HashMap<>();
    userInfo.put("user", user.getUserAuthentication().getPrincipal());
    userInfo.put("authorities",
        AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
    return userInfo;
  }
}
