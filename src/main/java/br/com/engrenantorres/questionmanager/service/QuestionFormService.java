package br.com.engrenantorres.questionmanager.service;

import br.com.engrenantorres.questionmanager.controller.QuestionFormController;
import br.com.engrenantorres.questionmanager.dto.NewQuestionDTO;
import br.com.engrenantorres.questionmanager.model.*;
import br.com.engrenantorres.questionmanager.model.enums.Cargo;
import br.com.engrenantorres.questionmanager.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionFormService {

  private final Logger LOGGER = LoggerFactory.getLogger(QuestionFormService.class);
  private QuestionRepository questionRepository;
  private BancaRepository bancaRepository;
  private SubjectAreaRepository areaRepository;
  private UserRepository userRepository;
  private AssuntoRepository assuntoRepository;

  @Autowired
  public QuestionFormService(QuestionRepository questionRepository,
                             BancaRepository bancaRepository,
                             SubjectAreaRepository areaRepository,
                             UserRepository userRepository,
                             AssuntoRepository assuntoRepository) {
    this.questionRepository = questionRepository;
    this.bancaRepository = bancaRepository;
    this.areaRepository = areaRepository;
    this.userRepository = userRepository;
    this.assuntoRepository = assuntoRepository;
  }

  public void insert(NewQuestionDTO newQuestionDTO) {

    LOGGER.info("insert question (enunciado : " + newQuestionDTO.getEnunciado() + ")");
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    Optional<User> user = userRepository.findByUsername(username);
    saveQuestionInDB(newQuestionDTO, user.get());
  }
  public void saveQuestionInDB(NewQuestionDTO newQuestionDTO, User author) {
    Question question = newQuestionDTO.toQuestion(author);
    questionRepository.save(question);
  }

  public void injectAttributesFromBD(Model model) {
    List<SubjectArea> areas = areaRepository.findAll();
    List<Banca> bancas = bancaRepository.findAll();
    //Assunto varia de acordo com a area. Então começa colocando os assunto de sst. Primeira area na tela.
    List<Assunto> assuntos = assuntoRepository.findAllBySubjectAreaId(1L);

    model.addAttribute("areas",areas);
    model.addAttribute("cargos", Cargo.values());
    model.addAttribute("bancas",bancas);
    model.addAttribute("assuntos",assuntos);
  }
}
