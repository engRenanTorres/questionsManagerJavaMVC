package br.com.engrenantorres.questionmanager.dto;

import br.com.engrenantorres.questionmanager.model.Answers;
import br.com.engrenantorres.questionmanager.model.Banca;
import br.com.engrenantorres.questionmanager.model.Question;
import br.com.engrenantorres.questionmanager.model.SubjectArea;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewQuestionDTO {
  private Long id = 0L;
  private SubjectArea cargo = new SubjectArea();
  private Banca banca = new Banca();
  @NotBlank
  private String enunciado = "";
  @NotBlank
  private String alternativa1 = "";
  @NotBlank
  private String alternativa2 = "";
  @NotBlank
  private String alternativa3 = "";
  @NotBlank
  private String alternativa4 = "";
  @NotBlank
  private String alternativa5 = "";
  private Answers resposta = Answers.a;

  public NewQuestionDTO() {
  }

  public NewQuestionDTO(Question question) {
    this.id = question.getId();
    this.cargo = question.getCargo();
    this.banca = question.getBanca();
    this.enunciado = question.getEnunciado();
    this.alternativa1 = question.getAlternativa1();
    this.alternativa2 = question.getAlternativa2();
    this.alternativa3 = question.getAlternativa3();
    this.alternativa4 = question.getAlternativa4();
    this.alternativa5 = question.getAlternativa5();
    this.resposta = question.getResposta();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SubjectArea getCargo() {
    return cargo;
  }

  public void setCargo(SubjectArea cargo) {
    this.cargo = cargo;
  }

  public Banca getBanca() {
    return banca;
  }

  public void setBanca(Banca banca) {
    this.banca = banca;
  }

  public String getEnunciado() {
    return enunciado;
  }

  public void setEnunciado(String enunciado) {
    this.enunciado = enunciado;
  }

  public String getAlternativa1() {
    return alternativa1;
  }

  public void setAlternativa1(String alternativa1) {
    this.alternativa1 = alternativa1;
  }

  public String getAlternativa2() {
    return alternativa2;
  }

  public void setAlternativa2(String alternativa2) {
    this.alternativa2 = alternativa2;
  }

  public String getAlternativa3() {
    return alternativa3;
  }

  public void setAlternativa3(String alternativa3) {
    this.alternativa3 = alternativa3;
  }

  public String getAlternativa4() {
    return alternativa4;
  }

  public void setAlternativa4(String alternativa4) {
    this.alternativa4 = alternativa4;
  }

  public String getAlternativa5() {
    return alternativa5;
  }

  public void setAlternativa5(String alternativa5) {
    this.alternativa5 = alternativa5;
  }

  public Answers getResposta() {
    return resposta;
  }

  public void setResposta(Answers resposta) {
    this.resposta = resposta;
  }



  public Question toQuestion() {
    Question question = new Question();
    question.setId(id);
    question.setCargo(cargo);
    question.setBanca(banca);
    question.setEnunciado(enunciado);
    question.setAlternativa1(alternativa1);
    question.setAlternativa2(alternativa2);
    question.setAlternativa3(alternativa3);
    question.setAlternativa4(alternativa4);
    question.setAlternativa5(alternativa5);
    question.setResposta(resposta);
    return question;
  }
}
