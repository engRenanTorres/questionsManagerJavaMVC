package br.com.engrenantorres.questionmanager.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
  @Id
  private String username;

  private String firstName;

  private String lastName;

  private String email;
  private String password;
  private Boolean enabled = true;
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

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }
}
