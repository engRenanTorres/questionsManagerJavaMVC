package br.com.engrenantorres.questionmanager.dto;

import br.com.engrenantorres.questionmanager.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDTO {
  private String username;
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User toUser(PasswordEncoder encoder) {
    User user = new User();
    user.setUsername(this.username);
    user.setPassword(encoder.encode(this.getPassword()));
    user.setEnabled(false);

    return user;
  }
}
