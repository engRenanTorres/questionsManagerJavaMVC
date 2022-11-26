package br.com.engrenantorres.questionmanager.controller;

import br.com.engrenantorres.questionmanager.service.QuestionListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;

@Controller
@RequestMapping("/questions-list")
public class QuestionsListController {

  private final Logger LOGGER = LoggerFactory.getLogger(QuestionsListController.class);

  private QuestionListService questionListService;
  @Autowired
  public QuestionsListController(QuestionListService questionListService) {
    this.questionListService = questionListService;
  }

  @GetMapping
  public String list(
    @RequestParam(name="areaId",required = false,defaultValue = "0") Long areaId,
    Model model,
    @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
    Principal principal
    ) {
    LOGGER.info("list()...");
    model.addAttribute("userName", principal.getName());
    questionListService.injectBancaAndAreaAttsFromBD(model);
    questionListService.HandleAttributes(model, page, areaId);

    return "questions-list";
  }

  @GetMapping("/my-questions")
  public String listMyQuestions(
    @RequestParam(name="areaId",required = false,defaultValue = "0") Long areaId,
    Model model,
    @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
    Principal principal
  ) {
    LOGGER.info("listMyQuetions()...");

    String username = principal.getName();
    model.addAttribute("userName", username);

    questionListService.injectBancaAndAreaAttsFromBD(model);
    model.addAttribute("areaId", "nulo");
    questionListService.getMyQuestionsList(model, page, username);

    return "questions-list";
  }



  @GetMapping("/edit/{questionId}")
  public String edit(
    Model model,
    Principal principal,
    @PathVariable("questionId") Long questionId,
    @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {

    LOGGER.info("edit( id = " + questionId + " )...");

    String username = principal.getName();
    model.addAttribute("userName", username);
    questionListService.getToEdit(model, questionId);

    return "question-form";
  }

  @GetMapping("/delete/{questionId}")
  public String remove(
    @PathVariable("questionId") Long questionId,
    Model model,
    @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {

    LOGGER.info("delete( id = " + questionId + " )...");
    questionListService.delete(questionId, model, page);
    return "redirect:/questions-list";
  }


  @ExceptionHandler(IllegalArgumentException.class)
  public String onError() {
    return "redirect:/questions-list";
  }


}
