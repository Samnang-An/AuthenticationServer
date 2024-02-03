package com.ankaboot.AuthenticationServer.controller;

import java.util.List;
import lombok.Getter;

@Getter
public class UserForm {

  private String username;
  private String password;
  private List<String> roles;

}
