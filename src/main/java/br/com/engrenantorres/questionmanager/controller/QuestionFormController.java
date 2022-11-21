package br.com.engrenantorres.questionmanager.controller;

import br.com.engrenantorres.questionmanager.dto.NewQuestionDTO;
import br.com.engrenantorres.questionmanager.model.*;
import br.com.engrenantorres.questionmanager.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/question-form")
public class QuestionFormController {

  private final Logger LOGGER = LoggerFactory.getLogger(QuestionFormController.class);
  @Autowired
  private QuestionRepository questionRepository;
  @Autowired
  private BancaRepository bancaRepository;
  @Autowired
  private SubjectAreaRepository areaRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private AssuntoRepository assuntoRepository;

  @GetMapping
  public String form(Model model,
                     NewQuestionDTO newQuestionDTO,
                     Principal principal,
                     @RequestParam(name = "areaId",required = true,defaultValue = "1")
                     Long areaId
  ) {
    LOGGER.info("form()...");
    model.addAttribute("userName", principal.getName());
    model.addAttribute("areaId", areaId);

    injectAttributesFromBD(model,areaId);
    return "question-form";
  }
  @PostMapping
  public String insert(@Valid NewQuestionDTO newQuestionDTO,
                       BindingResult bindingResult,
                       Long areaId,
                       Model model){
    if(bindingResult.hasErrors()) {
      injectAttributesFromBD(model,areaId);
      LOGGER.error("Validation error : " + bindingResult.getAllErrors());
      return "question-form";
    }
    LOGGER.info("insert question enunciado : " + newQuestionDTO.getEnunciado());
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    Optional<User> user = userRepository.findByUsername(username);
    saveQuestionInDB(newQuestionDTO, user.get());
    return "redirect:/questions-list";
  }

  private void saveQuestionInDB(NewQuestionDTO newQuestionDTO, User author) {
    Question question = newQuestionDTO.toQuestion(author);
    questionRepository.save(question);
  }

  private void injectAttributesFromBD(Model model,Long areaId) {
    List<SubjectArea> areas = areaRepository.findAll();
    List<Banca> bancas = bancaRepository.findAll();
    List<Assunto> assuntos = assuntoRepository.findAllByCargoId(areaId);

    model.addAttribute("areas",areas);
    model.addAttribute("bancas",bancas);
    model.addAttribute("assuntos",assuntos);
  }
}
