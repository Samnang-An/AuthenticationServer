package com.ankaboot.AuthenticationServer.dataaccess.repository;

import com.ankaboot.AuthenticationServer.dataaccess.document.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,Long> {

  Optional<User> findByUsername(String username);

  void deleteByUsername(String username);
}
