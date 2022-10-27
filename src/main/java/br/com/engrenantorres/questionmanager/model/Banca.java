package br.com.engrenantorres.questionmanager.model;

import br.com.engrenantorres.questionmanager.repository.BancaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Optional;

@Entity
public class Banca {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name = "";
  private String about = "";

  public Long getId() {
    return id;
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
  @Autowired
  private static BancaRepository bancaRepository;

  public static Optional<Banca> findById(Long id) {
    return bancaRepository.findById(id);
  }



}
