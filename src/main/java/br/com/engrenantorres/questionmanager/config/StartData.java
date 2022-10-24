package br.com.engrenantorres.questionmanager.config;

import br.com.engrenantorres.questionmanager.model.Banca;
import br.com.engrenantorres.questionmanager.model.Question;
import br.com.engrenantorres.questionmanager.model.SubjectArea;
import br.com.engrenantorres.questionmanager.repository.BancaRepository;
import br.com.engrenantorres.questionmanager.repository.QuestionRepository;
import br.com.engrenantorres.questionmanager.repository.SubjectAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
public class StartData implements CommandLineRunner {
  @Autowired
  private BancaRepository bancaRepository;
  @Autowired
  private SubjectAreaRepository areaRepository;
  @Autowired
  private QuestionRepository questionRepository;

  @Override
  public void run(String... args) throws Exception {
    addInitialBancaData();
    addInitialAreasData();
    addInitialQuestionData();

  }

  private void addInitialQuestionData() {
/*    if(questionRepository.count() == 0) {
      Question question = new Question();
      question.setEnunciado("Quem sou eu?");
      question.setAlternativa1("a) Eu");
      question.setAlternativa2("b) Tu");
      question.setAlternativa1("c) Ele");
      question.setAlternativa1("d) Nós");
      question.setAlternativa1("e) Vós");
      Optional<Banca> banca = bancaRepository.findById(1L);
      question.setBanca(banca);
      Optional<SubjectArea> area = areaRepository.findById(1L);
      question.setCargo(area);


      List<Question> questions = Arrays.asList(question);
      questionRepository.saveAll(questions);
    }*/
  }

  private void addInitialAreasData() {
    if(areaRepository.count() == 0) {
      SubjectArea area = new SubjectArea();
      area.setName("Segurança do Trabalho");
      area.setAbout("Atua na prevensão de acidentes laborais");

      SubjectArea area2 = new SubjectArea();
      area2.setName("Construção Civil");
      area2.setAbout("Atua na Construção de obras civis");

      List<SubjectArea> areas = Arrays.asList(area, area2);
      areaRepository.saveAll(areas);
    }
  }

  private void addInitialBancaData() {
    if(bancaRepository.count() == 0) {
      Banca banca = new Banca();
      banca.setName("F.G.V");
      banca.setAbout("Banca com questões multiplas escolha de 5 alternativas");

      Banca banca2 = new Banca();
      banca2.setName("CESPE");
      banca2.setAbout("Banca com questões de verdadeiro, ou falso.");

      List<Banca> bancas = Arrays.asList(banca, banca2);
      bancaRepository.saveAll(bancas);
    }
  }
}
