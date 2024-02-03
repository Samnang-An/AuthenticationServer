package com.ankaboot.AuthenticationServer.dataaccess.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "user")
public class User {

  @Transient
  public static final String SEQUENCE_NAME = "user_sequence";

  @Id
  private long userId;
  private String username;
  @JsonProperty(access = Access.WRITE_ONLY)
  private String password;
  private List<String> roles;
}
