package br.com.engrenantorres.questionmanager.dto;

import br.com.engrenantorres.questionmanager.model.Question;
import br.com.engrenantorres.questionmanager.model.Result;
import br.com.engrenantorres.questionmanager.model.User;
import br.com.engrenantorres.questionmanager.model.enums.AnswerResult;

public class ResultDTO {

  private Long questionId;

  private AnswerResult answerResult;

  public Long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Long questionId) {
    this.questionId = questionId;
  }

  public AnswerResult getAnswerResult() {
    return answerResult;
  }

  public void setAnswerResult(AnswerResult answerResult) {
    this.answerResult = answerResult;
  }

  public Result toResult(Question question, User user) {
    var result = new Result(question,this.answerResult, user);
    return result;
  }
}
