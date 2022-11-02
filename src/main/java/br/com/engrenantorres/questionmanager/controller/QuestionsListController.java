package br.com.engrenantorres.questionmanager.controller;

import br.com.engrenantorres.questionmanager.dto.NewQuestionDTO;
import br.com.engrenantorres.questionmanager.model.Banca;
import br.com.engrenantorres.questionmanager.model.Question;
import br.com.engrenantorres.questionmanager.model.SubjectArea;
import br.com.engrenantorres.questionmanager.repository.BancaRepository;
import br.com.engrenantorres.questionmanager.repository.QuestionRepository;
import br.com.engrenantorres.questionmanager.repository.SubjectAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/questions-list")
public class QuestionsListController {
  @Autowired
  private QuestionRepository questionRepository;
  @Autowired
  private SubjectAreaRepository areaRepository;
  @Autowired
  BancaRepository bancaRepository;

  private Integer paginationSize = 5;


  @GetMapping
  public String list(
    @RequestParam(name="areaId",required = false,defaultValue = "0") Long areaId,
    Model model,
    @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
    Principal principal
    ) {

    model.addAttribute("userName", principal.getName());

    injectBancaAndAreaAttsFromBD(model);

    HandleAttributes(model, page, areaId);

    return "questions-list";
  }

  @GetMapping("/my-questions")
  public String listMyQuestions(
    @RequestParam(name="areaId",required = false,defaultValue = "0") Long areaId,
    Model model,
    @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
    Principal principal
  ) {
    Sort sort = Sort.by("cargo").ascending()
      .and(Sort.by("date").descending());

    String username = principal.getName();
    model.addAttribute("userName", username);


    injectBancaAndAreaAttsFromBD(model);

    Page<Question> questions;
    model.addAttribute("areaId", "nulo");
    questions = questionRepository.findAllByAuthor(username,PageRequest.of(page, paginationSize, sort));

    injectQuestionsAttributes(model, page, questions);

    return "questions-list";
  }

  @GetMapping("/edit/{questionId}")
  public String edit(
    @PathVariable("questionId") Long questionId,
    Model model,
    @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
    Optional<Question> question = questionRepository.findById(questionId);
    injectQuestionDataIntoEditForm(model, question);

    injectBancaAndAreaAttsFromBD(model);

    return "question-form";
  }


  @GetMapping("/delete/{questionId}")
  public String remove(
    @PathVariable("questionId") Long questionId,
    Model model,
    @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
    questionRepository.deleteById(questionId);
    HandleAttributes(model, page, 0L);
    return "redirect:/questions-list";
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public String onError() {
    return "redirect:/questions-list";
  }
  private void injectQuestionDataIntoEditForm(Model model, Optional<Question> question) {
    question.ifPresent(question1 -> {
      NewQuestionDTO questionDTO = new NewQuestionDTO(question1);
      model.addAttribute("newQuestionDTO", questionDTO);
    });
  }

  private void HandleAttributes(Model model, Integer page, Long areaId) {
    Page<Question> questions;
    questions = getQuestionsApplyingFilterIfExist(model, page, areaId);

    injectQuestionsAttributes(model, page, questions);
  }

  private void injectQuestionsAttributes(Model model, Integer page, Page<Question> questions) {
    Integer nextPage = (page >= (questions.getTotalElements() - 1) / paginationSize) ? page : page + 1;
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
      questions = questionRepository.findAll(PageRequest.of(page, paginationSize));
    } else {
      model.addAttribute("areaId", areaId);
      Optional<SubjectArea> area = areaRepository.findById(areaId);
      questions = questionRepository.findByCargoOrderByDateAsc(area.get(), PageRequest.of(page, paginationSize));
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
