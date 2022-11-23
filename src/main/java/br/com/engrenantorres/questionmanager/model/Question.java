package br.com.engrenantorres.questionmanager.model;

import br.com.engrenantorres.questionmanager.model.enums.Alternatives;
import br.com.engrenantorres.questionmanager.model.enums.Cargo;
import br.com.engrenantorres.questionmanager.repository.QuestionRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Question {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Enumerated(EnumType.STRING)
  private Cargo cargo = Cargo.Engenharia;

  private Nivel nivel = Nivel.Superior;
  @ManyToOne
  private Banca banca = new Banca();
  @ManyToOne
  private SubjectArea subjectArea;
  @ManyToOne
  private Assunto assunto;
  private String enunciado = "";

  private String alternativa1 = "";

  private String alternativa2 = "";

  private String alternativa3 = "";

  private String alternativa4 = "";

  private String alternativa5 = "";

  private Integer ano = 2022;
  private String concurso = "";
  @Enumerated(EnumType.STRING)
  private Alternatives resposta = Alternatives.A;
  private String observacao = "";
  private LocalDateTime date = LocalDateTime.now();

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private User author;

  @OneToMany(cascade = CascadeType.ALL,
    mappedBy = "questionDone",fetch = FetchType.LAZY
  )
  private List<Result> results;


  public Question(User author) {
    this.author = author;
  }
  public Question() {
  }

  public Question(Cargo cargo,
                  Nivel nivel,
                  Banca banca,
                  SubjectArea subjectArea,
                  Assunto assunto,
                  String enunciado,
                  String alternativa1,
                  String alternativa2,
                  String alternativa3,
                  String alternativa4,
                  String alternativa5,
                  Integer ano,
                  String concurso,
                  Alternatives resposta,
                  User author) {
    this.cargo = cargo;
    this.nivel = nivel;
    this.banca = banca;
    this.subjectArea = subjectArea;
    this.assunto = assunto;
    this.enunciado = enunciado;
    this.alternativa1 = alternativa1;
    this.alternativa2 = alternativa2;
    this.alternativa3 = alternativa3;
    this.alternativa4 = alternativa4;
    this.alternativa5 = alternativa5;
    this.ano = ano;
    this.concurso = concurso;
    this.resposta = resposta;
    this.author = author;
  }

  public List<Result> getResults() {
    return results;
  }

  public void setResults(List<Result> results) {
    this.results = results;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Banca getBanca() {
    return banca;
  }

  public void setBanca(Banca banca) {
    this.banca = banca;
  }

  public SubjectArea getSubjectArea() {
    return subjectArea;
  }

  public Cargo getCargo() {
    return cargo;
  }

  public void setCargo(Cargo cargo) {
    this.cargo = cargo;
  }
  public void setSubjectArea(SubjectArea subjectArea) {
    this.subjectArea = subjectArea;
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

  public void setAlternativa1(String alternativa) {
    this.alternativa1 = alternativa;
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

  public Integer getAno() {
    return ano;
  }

  public void setAno(Integer ano) {
    this.ano = ano;
  }

  public String getConcurso() {
    return concurso;
  }

  public void setConcurso(String concurso) {
    this.concurso = concurso;
  }

  public Assunto getAssunto() {
    return this.assunto;
  }

  public void setAssunto(Assunto assunto) {
    this.assunto = assunto;
  }

  public Alternatives getResposta() {
    return resposta;
  }

  public void setResposta(Alternatives resposta) {
    this.resposta = resposta;
  }

  public String getObservacao() {
    return observacao;
  }

  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  @Autowired
  private static QuestionRepository questionRepository;

  public static List<Question> getAll(){
    return questionRepository.findAll();
  }
}
