package com.ankaboot.AuthenticationServer.dataaccess.service;

import com.ankaboot.AuthenticationServer.dto.UserDto;
import java.util.List;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

  UserDto createUser(String username, String password, List<String> roles);

  UserDto updateUser(String username, String passowrd, List<String> roles);

  UserDto updateUser(long id, String username, String passowrd, List<String> roles);

  void deleteUser(String username);

  UserDto fetchUser(String username);

  List<UserDto> findAll();

  void deleteUser(long userId);
}
