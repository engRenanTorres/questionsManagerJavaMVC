package br.com.engrenantorres.questionmanager.model;

import br.com.engrenantorres.questionmanager.model.enums.AnswerResult;
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
  private Question questionDone;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_username")
  private User user;
  @Enumerated(EnumType.STRING)
  private AnswerResult answerResult;

  private LocalDateTime date = LocalDateTime.now();

  public User getUser() {
    return user;
  }

  public Result() {
  }

  public Result(Question questionDone, AnswerResult answerResult, User user) {
    this.questionDone = questionDone;
    this.answerResult = answerResult;
    this.user = user;
  }

}
