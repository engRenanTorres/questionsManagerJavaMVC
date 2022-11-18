package br.com.engrenantorres.questionmanager.config;

import br.com.engrenantorres.questionmanager.model.*;
import br.com.engrenantorres.questionmanager.model.enums.Alternatives;
import br.com.engrenantorres.questionmanager.repository.*;
import br.com.engrenantorres.questionmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class StartData implements CommandLineRunner {
  @Autowired
  private BancaRepository bancaRepository;
  @Autowired
  private SubjectAreaRepository areaRepository;
  @Autowired
  private QuestionRepository questionRepository;
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private UserService userService;

  @Override
  public void run(String... args) throws Exception {
    addInitialRolesData();

    Role role = roleRepository.findAll().get(0);
    User user = new User();
    user.setUsername("admin");
    user.setPassword("123456");
    user.setEmail("admin@admin.com");
    user.addRole(role);
    userService.registerUser(user);

    addInitialBancaData();
    addInitialAreasData();
    addInitialQuestionData();

  }

  private void addInitialRolesData() {
    if(roleRepository.count() == 0){
      var user = new Role("USER");
      var prof = new Role("PROF");
      var adm = new Role("ADM");
      roleRepository.saveAll(List.of(adm,prof,user));
    }

  }


  private void addInitialAreasData() {
    if(areaRepository.count() == 0) {
      var area = new SubjectArea();
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
      var banca = new Banca();
      banca.setName("F.G.V");
      banca.setAbout("Banca com questões multiplas escolha de 5 alternativas");

      var banca2 = new Banca();
      banca2.setName("CESPE");
      banca2.setAbout("Banca com questões de verdadeiro, ou falso.");

      List<Banca> bancas = Arrays.asList(banca, banca2);
      bancaRepository.saveAll(bancas);
    }
  }
  private void addInitialQuestionData() {
    if(questionRepository.count() == 0) {
      List<SubjectArea> areas = areaRepository.findAll();
      List<Banca> bancas = bancaRepository.findAll();
      List<User> users = userRepository.findAll();

      Question question = new Question(users.get(0));
      question.setEnunciado("Quem sou eu?");
      question.setAlternativa1("a) Eu");
      question.setAlternativa2("b) Tu");
      question.setAlternativa3("c) Ele");
      question.setAlternativa4("d) Nós");
      question.setAlternativa5("e) Vós");
      question.setResposta(Alternatives.A);
      Banca banca = bancas.get(0);
      question.setBanca(banca);
      SubjectArea area = areas.get(0);
      question.setCargo(area);

      Question question1 = new Question(users.get(0));
      Banca banca2 = bancas.get(1);
      question1.setBanca(banca2);
      SubjectArea area2 = areas.get(1);
      question1.setCargo(area2);
      question1.setEnunciado("Quem és tu?");
      question1.setAlternativa1("a) Eu");
      question1.setAlternativa2("b) Tu");
      question1.setAlternativa3("c) Ele");
      question1.setAlternativa4("d) Nós");
      question1.setAlternativa5("e) Vós");
      question.setResposta(Alternatives.B);


      List<Question> questions = Arrays.asList(question, question1);
      questionRepository.saveAll(questions);
    }
  }
}
