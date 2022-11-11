package br.com.engrenantorres.questionmanager.dto;

import br.com.engrenantorres.questionmanager.model.Question;
import br.com.engrenantorres.questionmanager.model.Result;
import br.com.engrenantorres.questionmanager.model.User;
import br.com.engrenantorres.questionmanager.model.enums.Alternatives;

public class ResultDTO {

  private Long questionId;

  private Boolean answerResult;

  private Alternatives answerMarked;



  public Alternatives getAnswerMarked() {
    return answerMarked;
  }

  public void setAnswerMarked(Alternatives answerMarked) {
    this.answerMarked = answerMarked;
  }

  public Long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Long questionId) {
    this.questionId = questionId;
  }

  public Boolean getAnswerResult() {
    return answerResult;
  }

  public void setAnswerResult(Boolean answerResult) {
    this.answerResult = answerResult;
  }

  public Result toResult(Question question, User user) {
    var result = new Result(question,this.answerResult, user, this.answerMarked);
    return result;
  }
}
