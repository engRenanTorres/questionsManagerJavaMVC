package br.com.engrenantorres.questionmanager.model;

import br.com.engrenantorres.questionmanager.model.enums.Alternatives;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Result {
  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_done_id")
  @JsonIgnore
  private Question questionDone;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_username")
  @JsonIgnore
  private User user;
  @Enumerated(EnumType.STRING)
  private Alternatives answerMarked;

  private Boolean answerResult;

  private final LocalDateTime date = LocalDateTime.now();



  public User getUser() {
    return user;
  }

  public Result() {
  }

  public Result(Question questionDone,
                Boolean answerResult, User user,
                Alternatives answerMarked) {
    this.questionDone = questionDone;
    this.answerResult = answerResult;
    this.user = user;
    this.answerMarked = answerMarked;
  }

  public Long getId() {
    return id;
  }

  public Question getQuestionDone() {
    return questionDone;
  }

  public Alternatives getAnswerMarked() {
    return answerMarked;
  }

  public Boolean getAnswerResult() {
    return answerResult;
  }

  public LocalDateTime getDate() {
    return date;
  }
}
