package br.com.engrenantorres.questionmanager.controller;

import br.com.engrenantorres.questionmanager.dto.NewQuestionDTO;
import br.com.engrenantorres.questionmanager.service.QuestionFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/question-form")
public class QuestionFormController {

  private final Logger LOGGER = LoggerFactory.getLogger(QuestionFormController.class);

  private QuestionFormService questionFormService;

  @Autowired
  public QuestionFormController(QuestionFormService questionFormService) {
    this.questionFormService = questionFormService;
  }

  @GetMapping
  public String form(Model model,
                     NewQuestionDTO newQuestionDTO,
                     Principal principal
  ) {
    LOGGER.info("form()...");
    model.addAttribute("userName", principal.getName());
    questionFormService.injectAttributesFromBD(model);
    return "question-form";
  }
  @PostMapping
  public String insert(@Valid NewQuestionDTO newQuestionDTO,
                       BindingResult bindingResult,
                       Model model){
    LOGGER.info("Saving question");
    if(bindingResult.hasErrors()) {
      questionFormService.injectAttributesFromBD(model);
      LOGGER.error("Validation error : " + bindingResult.getAllErrors());
      return "question-form";
    }
    questionFormService.insert(newQuestionDTO);
    LOGGER.info("Question saved successfully");
    return "redirect:/questions-list";
  }

}
