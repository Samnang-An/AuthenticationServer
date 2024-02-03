package com.ankaboot.AuthenticationServer.dto;

import com.ankaboot.AuthenticationServer.dataaccess.document.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

  private long userId;
  private String username;
  private List<String> roles;

  public static List<UserDto> from(List<User> users) {
    return users.stream().map(UserDto::from).collect(Collectors.toList());
  }

  public static UserDto from(User user) {
    return UserDto.builder()
        .userId(user.getUserId())
        .username(user.getUsername())
        .roles(user.getRoles())
        .build();
  }
}
