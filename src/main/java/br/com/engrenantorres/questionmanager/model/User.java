package br.com.engrenantorres.questionmanager.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
  @Id
  private String username;

  private String firstName = "";

  private String lastName = "";
  private String email = "";
  private String password = "";
  private Boolean enabled = true;
  @ManyToMany(fetch = FetchType.EAGER)
  private List<Role> roles = new ArrayList<Role>();

  @OneToMany(cascade = CascadeType.ALL,
    mappedBy = "author",fetch = FetchType.LAZY)
  private List<Question> questionsPublished;
  @OneToMany(cascade = CascadeType.ALL,
    mappedBy = "user",fetch = FetchType.LAZY)
  private List<Result> results;



  public List<Result> getResults() {
    return results;
  }

  public void setResults(List<Result> results) {
    this.results = results;
  }

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

  public List<Question> getQuestionsPublished() {
    return questionsPublished;
  }

  public void setQuestionsPublished(List<Question> questionsPublished) {
    this.questionsPublished = questionsPublished;
  }

  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.roles;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean getEnabled() {
    return this.enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }
}
