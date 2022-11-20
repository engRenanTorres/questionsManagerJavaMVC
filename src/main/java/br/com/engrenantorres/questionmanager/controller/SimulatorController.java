package br.com.engrenantorres.questionmanager.controller;

import br.com.engrenantorres.questionmanager.model.Banca;
import br.com.engrenantorres.questionmanager.model.Question;
import br.com.engrenantorres.questionmanager.model.SubjectArea;
import br.com.engrenantorres.questionmanager.repository.BancaRepository;
import br.com.engrenantorres.questionmanager.repository.QuestionRepository;
import br.com.engrenantorres.questionmanager.repository.SubjectAreaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/simulator")
public class SimulatorController {

  private final Logger LOGGER = LoggerFactory.getLogger(SimulatorController.class);
  @Autowired
  private QuestionRepository questionRepository;
  @Autowired
  private SubjectAreaRepository areaRepository;
  @Autowired
  BancaRepository bancaRepository;


  @GetMapping("init")
  public String getSimulator(
    @RequestParam(name="areaId",required = true,defaultValue = "0") Long areaId,
    Model model,
    @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
    Principal principal
  ) {
    LOGGER.info("getSimulator()...");

    model.addAttribute("userName", principal.getName());


    injectBancaAndAreaAttsFromBD(model);

    HandleAttributes(model, page, areaId);
    return "simulator/simulator";
  }


  private void HandleAttributes(Model model, Integer page, Long areaId) {
    Page<Question> questions;
    questions = getQuestionsApplyingFilterIfExist(model, page, areaId);

    injectQuestionsAttributes(model, page, questions);
  }

  private void injectQuestionsAttributes(Model model, Integer page, Page<Question> questions) {
    Integer nextPage = (page >= (questions.getTotalElements() - 1) / 1) ? page : page + 1;
    Integer previousPage = (page <= 0) ? 0 : page - 1;
    model.addAttribute("questions", questions);
    model.addAttribute("nextPage", nextPage);
    model.addAttribute("previousPage", previousPage);
    model.addAttribute("isFirst", questions.isFirst());
    model.addAttribute("isLast", questions.isLast());
  }

  private Page<Question> getQuestionsApplyingFilterIfExist(Model model, Integer page, Long areaId) {
    Page<Question> questions;
    if (areaId == 0) {
      model.addAttribute("areaId", "nulo");
      questions = questionRepository.findAll(PageRequest.of(page, 1));
    } else {
      model.addAttribute("areaId", areaId);
      Optional<SubjectArea> area = areaRepository.findById(areaId);
      questions = questionRepository.findByCargoOrderByDateAsc(area.get(), PageRequest.of(page, 1));
    }
    return questions;
  }

  private void injectBancaAndAreaAttsFromBD(Model model) {
    List<SubjectArea> areas = areaRepository.findAll();
    List<Banca> bancas = bancaRepository.findAll();
    model.addAttribute("areas", areas);
    model.addAttribute("bancas", bancas);
  }
}
