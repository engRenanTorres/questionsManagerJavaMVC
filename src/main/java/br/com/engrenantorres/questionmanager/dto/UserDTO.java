package br.com.engrenantorres.questionmanager.dto;

import br.com.engrenantorres.questionmanager.model.User;

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

  public User toUser() {
    User user = new User();
    user.setUsername(this.username);
    user.setPassword(this.getPassword());
    user.setEnabled(false);

    return user;
  }
}
