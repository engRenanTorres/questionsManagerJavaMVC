package br.com.engrenantorres.questionmanager.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class SubjectArea {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name = "";
  private String about = "";

  @OneToMany
  private Set<Assunto> assuntos = new HashSet<>();

  public void addAssunto(Assunto assunto) {
    this.assuntos.add(assunto);
  }

  public Set<Assunto> getAssunto() {
    return assuntos;
  }

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
