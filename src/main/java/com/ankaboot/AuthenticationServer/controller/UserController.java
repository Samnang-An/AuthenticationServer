package com.ankaboot.AuthenticationServer.controller;

import com.ankaboot.AuthenticationServer.dataaccess.service.UserService;
import com.ankaboot.AuthenticationServer.dto.UserDto;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/create_user")
  public ResponseEntity<Object> createUser(@RequestBody UserForm user) {
    try {
      return ResponseEntity.ok().body(userService.createUser(
          user.getUsername(),
          user.getPassword(),
          user.getRoles()
      ));
    } catch (DuplicateKeyException dke) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dke.getMessage());
    }
  }

  @PutMapping("/update_user/{userId}")
  public ResponseEntity<Object> updateUserById(@PathVariable long userId,
      @RequestBody UserForm user) {
    UserDto userDto;
    try {
      userDto = userService.updateUser(
          userId,
          user.getUsername(),
          user.getPassword(),
          user.getRoles()
      );
    } catch (UsernameNotFoundException unf) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(unf.getMessage());
    }
    return ResponseEntity.ok(userDto);
  }

  @PutMapping("/update_user")
  public ResponseEntity<Object> updateUser(@RequestBody UserForm user) {
    UserDto userDto;
    try {
      userDto = userService.updateUser(
          user.getUsername(),
          user.getPassword(),
          user.getRoles()
      );
    } catch (UsernameNotFoundException unf) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(unf.getMessage());
    }
    return ResponseEntity.ok(userDto);
  }

  @DeleteMapping("/delete_user/{userId}")
  public ResponseEntity<String> deleteUser(@PathVariable long userId) {
    try {
      userService.deleteUser(userId);
    } catch (UsernameNotFoundException unf) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found!");
    }
    return ResponseEntity.ok("Delete Successful!!");
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
