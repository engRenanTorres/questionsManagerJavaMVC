package br.com.engrenantorres.questionmanager.model;

import javax.persistence.*;

@Entity
@Table(name = "question_author")
public class QuestionAuthor {
  @Id
  @GeneratedValue
  private Long id = 0L;
  private String about = "";
  @OneToOne
  private User user = new User();


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAbout() {
    return about;
  }

  public void setAbout(String about) {
    this.about = about;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
