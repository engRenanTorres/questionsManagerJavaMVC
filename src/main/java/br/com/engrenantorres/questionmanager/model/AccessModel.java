package br.com.engrenantorres.questionmanager.model;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
public class AccessModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  private String path;
  private LocalDateTime date;
  private Duration duration;

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public Duration getDuration() {
    return duration;
  }

  public void setDuration(Duration duration) {
    this.duration = duration;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
