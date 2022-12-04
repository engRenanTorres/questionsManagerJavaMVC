package br.com.engrenantorres.questionmanager.model;

import javax.persistence.*;

@Entity
public class Banca {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name = "";
  private String about = "";
  @Embedded
  private Contact contact = new Contact();

  public void setId(Long id) {
    this.id = id;
  }

  public String getOfficialSite() {
    return this.contact.getOfficialSite();
  }

  public void setOfficialSite(String officialSite) {
    this.contact.setOfficialSite(officialSite);
  }

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



}
