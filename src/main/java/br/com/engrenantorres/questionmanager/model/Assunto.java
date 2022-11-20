package br.com.engrenantorres.questionmanager.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Assunto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  private String name = "";

  @OneToMany(mappedBy = "assunto")
  private List<Question> questions = new ArrayList<>();

  @ManyToOne
  private SubjectArea cargo;

  public Assunto() {
  }

  public Assunto(String name, SubjectArea cargo, String description) {
    this.name = name;
    this.cargo = cargo;
    this.description = description;
  }

  private String description = "";

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
