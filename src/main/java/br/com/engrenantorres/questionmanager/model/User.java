package br.com.engrenantorres.questionmanager.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
  @Id
  private String username;
  private String password;
  private Boolean enabled;
  @OneToMany(cascade = CascadeType.ALL,mappedBy = "author",fetch = FetchType.LAZY)
  private List<Question> questionsPublished;

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
