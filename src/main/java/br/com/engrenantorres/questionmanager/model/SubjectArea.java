package br.com.engrenantorres.questionmanager.model;

import javax.persistence.*;

@Entity
public class SubjectArea {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name = "";
  private String about = "";

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAbout() {
    return about;
  }

  public void setAbout(String about) {
    this.about = about;
  }

  public Long getId() {
    return id;
  }


}
