package br.com.engrenantorres.questionmanager.dto;

import br.com.engrenantorres.questionmanager.model.*;
import br.com.engrenantorres.questionmanager.model.enums.Alternatives;
import br.com.engrenantorres.questionmanager.model.enums.Cargo;
import br.com.engrenantorres.questionmanager.model.enums.Nivel;

import javax.validation.constraints.NotBlank;

public class NewQuestionDTO {
  private Long id = 0L;
  private Cargo cargo = Cargo.Engenharia;
  private Banca banca = new Banca();
  private SubjectArea subjectArea = new SubjectArea();
  private String concurso = "";

  private Assunto assunto = new Assunto();
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
  private Alternatives resposta = Alternatives.A;
  private String observacao = "";

  private Integer ano = 2022;

  public NewQuestionDTO() {
  }

  public NewQuestionDTO(Question question) {
    this.id = question.getId();
    this.subjectArea = question.getSubjectArea();
    this.cargo = question.getCargo();
    this.banca = question.getBanca();
    this.assunto = question.getAssunto();
    this.enunciado = question.getEnunciado();
    this.concurso = question.getConcurso();
    this.ano = question.getAno();
    this.alternativa1 = question.getAlternativa1();
    this.alternativa2 = question.getAlternativa2();
    this.alternativa3 = question.getAlternativa3();
    this.alternativa4 = question.getAlternativa4();
    this.alternativa5 = question.getAlternativa5();
    this.resposta = question.getResposta();
    this.observacao = question.getObservacao();
  }

  public Cargo getCargo() {
    return cargo;
  }

  public void setCargo(Cargo cargo) {
    this.cargo = cargo;
  }

  public Integer getAno() {
    return ano;
  }

  public void setAno(Integer ano) {
    this.ano = ano;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getConcurso() {
    return concurso;
  }

  public void setConcurso(String concurso) {
    this.concurso = concurso;
  }

  public String getObservacao() {
    return observacao;
  }

  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }

  public SubjectArea getSubjectArea() {
    return subjectArea;
  }

  public void setSubjectArea(SubjectArea subjectArea) {
    this.subjectArea = subjectArea;
  }

  public Assunto getAssunto() {
    return assunto;
  }

  public void setAssunto(Assunto assunto) {
    this.assunto = assunto;
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

  public Alternatives getResposta() {
    return resposta;
  }

  public void setResposta(Alternatives resposta) {
    this.resposta = resposta;
  }



  public Question toQuestion(User author) {
    Question question = new Question(author);
    question.setId(id);
    question.setSubjectArea(subjectArea);
    question.setCargo(cargo);
    if(cargo == Cargo.Engenharia) question.setNivel(Nivel.Superior);
    if(cargo == Cargo.Técnico) question.setNivel(Nivel.Médio);
    question.setBanca(banca);
    question.setAssunto(assunto);
    question.setConcurso(concurso);
    question.setEnunciado(enunciado);
    question.setAlternativa1(alternativa1);
    question.setAlternativa2(alternativa2);
    question.setAlternativa3(alternativa3);
    question.setAlternativa4(alternativa4);
    question.setAlternativa5(alternativa5);
    question.setResposta(resposta);
    question.setObservacao(observacao);
    question.setAno(ano);

    return question;
  }
}
