package com.ankaboot.AuthenticationServer.dataaccess.service.impl;

import com.ankaboot.AuthenticationServer.dataaccess.document.User;
import com.ankaboot.AuthenticationServer.dataaccess.repository.UserRepository;
import com.ankaboot.AuthenticationServer.dataaccess.service.SequenceGenerator;
import com.ankaboot.AuthenticationServer.dataaccess.service.UserService;
import com.ankaboot.AuthenticationServer.dto.UserDto;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

  private final String USER_NOT_FOUND_MSG = "Username is not found";
  @Autowired
  UserRepository userRepository;

  @Autowired
  SequenceGenerator sequenceGenerator;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDto createUser(String username, String password, List<String> roles) {
    User existingUser = userRepository.findByUsername(username).orElse(null);
    if (!Objects.isNull(existingUser)) {
      throw new DuplicateKeyException("Username is already exist!");
    }
    User user = new User(
        sequenceGenerator.generate(User.SEQUENCE_NAME),
        username,
        passwordEncoder.encode(password),
        roles);
    return UserDto.from(userRepository.save(user));
  }

  @Override
  public UserDto updateUser(String username, String password, List<String> roles) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MSG));
    user.setPassword(passwordEncoder.encode(password));
    user.setRoles(roles);
    return UserDto.from(userRepository.save(user));
  }

  @Override
  public UserDto updateUser(long userId, String username, String password, List<String> roles) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MSG));
    user.setPassword(passwordEncoder.encode(password));
    user.setRoles(roles);
    return UserDto.from(userRepository.save(user));
  }

  @Override
  public void deleteUser(String username) {
    userRepository.deleteByUsername(username);
  }

  @Override
  public UserDto fetchUser(String username) {
    return UserDto.from(userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MSG)));
  }

  @Override
  public List<UserDto> findAll() {
    return UserDto.from(userRepository.findAll());
  }

  @Override
  public void deleteUser(long userId) {
    userRepository.deleteById(userId);
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MSG));
    return new org.springframework.security.core.userdetails.User(
        user.getUsername(),
        user.getPassword(),
        user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
    );
  }
}
