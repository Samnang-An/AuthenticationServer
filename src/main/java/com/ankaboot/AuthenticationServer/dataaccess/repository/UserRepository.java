package com.ankaboot.AuthenticationServer.dataaccess.repository;

import com.ankaboot.AuthenticationServer.dataaccess.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,Long> {

  User findByUsername(String username);

  void deleteByUsername(String username);
}
