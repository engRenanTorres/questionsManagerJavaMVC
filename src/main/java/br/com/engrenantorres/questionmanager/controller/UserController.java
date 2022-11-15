package br.com.engrenantorres.questionmanager.controller;

import br.com.engrenantorres.questionmanager.model.User;
import br.com.engrenantorres.questionmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
  private UserService userService;
  @Autowired
  public UserController(UserService userService){
    this.userService = userService;
  }

}
