package br.com.engrenantorres.questionmanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
  private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
  @GetMapping("/login")
  String login(){
    LOGGER.info("login()...");
    return "login";
  }
}
