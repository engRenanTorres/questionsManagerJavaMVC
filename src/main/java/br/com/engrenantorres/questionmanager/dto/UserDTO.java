package br.com.engrenantorres.questionmanager.dto;

import br.com.engrenantorres.questionmanager.model.Role;
import br.com.engrenantorres.questionmanager.model.User;
import br.com.engrenantorres.questionmanager.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserDTO {

  private String username;

  private String firstName;
  private String lastName;
  private String email;
  private String password;


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

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
    user.setEmail(this.email);
    user.setFirstName(this.firstName);
    user.setLastName(this.lastName);
    user.setPassword(this.getPassword());
    user.setFirstName(this.firstName);
    user.setLastName(this.lastName);
    user.setEmail(this.email);
    user.setEnabled(false);

    return user;
  }
}
