package br.com.engrenantorres.questionmanager.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {
  @Id
  @Column(columnDefinition = "varchar(50)")
  private String username;
  @Column(columnDefinition = "varchar(50)")
  private String firstName = "";
  @Column(columnDefinition = "varchar(50)")
  private String lastName = "";
  @Column(nullable = false, unique = true, length = 45,columnDefinition = "varchar(50)")
  private String email = "";
  private String password = "";
  private Boolean enabled = true;
  @Embedded
  private Contact contact = new Contact();
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(name = "user_username"),
      inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Role> roles = new HashSet<>();

  @OneToMany(cascade = CascadeType.ALL,
    mappedBy = "author",fetch = FetchType.LAZY)
  private List<Question> questionsPublished;
  @OneToMany(cascade = CascadeType.ALL,
    mappedBy = "user",fetch = FetchType.LAZY)
  private List<Result> results;

  public User() {
  }

  public void addRole(Role role) {
    this.roles.add(role);
  }

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

  public Set<Role> getRoles() {
    return roles;
  }

}
