package br.com.engrenantorres.questionmanager.config;

import br.com.engrenantorres.questionmanager.model.*;
import br.com.engrenantorres.questionmanager.model.enums.Alternatives;
import br.com.engrenantorres.questionmanager.model.enums.Cargo;
import br.com.engrenantorres.questionmanager.repository.*;
import br.com.engrenantorres.questionmanager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
  @Autowired
  private AssuntoRepository assuntoRepository;

  private final Logger LOGGER = LoggerFactory.getLogger(StartData.class);

  @Override
  public void run(String... args) throws Exception {
    addInitialRolesData();
    addInitialUser();
    addInitialBancaData();
    addInitialAreasData();
    addInitialAssuntosData();
    addInitialQuestionData();

  }


  private void addInitialUser() throws Exception {
    if (userRepository.count() == 0) {
      Role role = roleRepository.findAll().get(0);
      User user = new User();
      user.setUsername("admin");
      user.setPassword("123456");
      user.setEmail("admin@admin.com");
      user.addRole(role);
      try {
        userService.registerUser(user);
      } catch (Exception e) {
        LOGGER.error(e.getMessage());
      }
    }
  }
  private void addInitialAssuntosData() {
    if(assuntoRepository.count() == 0) {
      SubjectArea area = areaRepository.findAll().get(0);
      SubjectArea area2 = areaRepository.findAll().get(1);
      Assunto nr10 = new Assunto("NR10", area, "Norma regulamentodora de Trabalho com instalações energizadas");
      Assunto construcao = new Assunto("Construção", area2, "Construção civil");
      assuntoRepository.saveAll (Arrays.asList(nr10,construcao));
      LOGGER.info("New inital assunto added" + area.getName());
    }
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
      List<Assunto> assuntos = assuntoRepository.findAll();
      Banca banca = bancas.get(0);
      SubjectArea area = areas.get(0);

      Question question =
          new Question(
            Cargo.Técnico,
            Nivel.Médio,
            banca,
            area,
            assuntos.get(0),
            "Quem sou eu?",
            "a) Eu",
            "b) Tu",
            "c) Ele",
            "d) Nós",
            "e) Vós",
            2022,
            "Petrobras",
            Alternatives.C,
            users.get(0)
          );

      Banca banca2 = bancas.get(1);
      SubjectArea area2 = areas.get(1);
      Question question1 =
          new Question(
              Cargo.Engenharia,
              Nivel.Superior,
              banca2,
              area2,
              assuntos.get(1),
              "Quem és tu?",
              "a) Eu",
              "b) Tu",
              "c) Ele",
              "d) Nós",
              "e) Vós",
              2022,
              "Fundação Saúde RJ",
              Alternatives.B,
              users.get(0)
          );


      List<Question> questions = Arrays.asList(question, question1);
      questionRepository.saveAll(questions);
    }
  }
}
