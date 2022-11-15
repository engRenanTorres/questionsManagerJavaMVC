package br.com.engrenantorres.questionmanager.controller;

import br.com.engrenantorres.questionmanager.dto.NewQuestionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class IndexController {
  private final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
  @GetMapping
  String index(
      Model model
  ){
    LOGGER.info("index()...");
    if(SecurityContextHolder.getContext().getAuthentication().getName() != null) {
      String username = SecurityContextHolder.getContext().getAuthentication().getName();

      model.addAttribute("userName", username);

    }
    return "index";
  }
}
