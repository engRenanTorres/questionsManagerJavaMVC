package br.com.engrenantorres.questionmanager.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class Role implements GrantedAuthority {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  @Column(nullable = false, length = 45, unique = true)
  private String name;

  public Role() { }

  public Role(String name) {
    this.name = "ROLE_" + name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String getAuthority() {
    return this.name;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
