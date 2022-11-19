package br.com.engrenantorres.questionmanager.controller;

import br.com.engrenantorres.questionmanager.dto.UserDTO;
import br.com.engrenantorres.questionmanager.model.User;
import br.com.engrenantorres.questionmanager.repository.UserRepository;
import br.com.engrenantorres.questionmanager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {
  @Autowired
  private UserService userService;

  private final Logger LOGGER = LoggerFactory.getLogger(SignUpController.class);
  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public String form(Model model) {
    LOGGER.info("form()...");
    model.addAttribute("userDTO", new UserDTO());
    return "signup";
  }

  @PostMapping
  public String saveUser(UserDTO userDTO,
                         String confirmPassword,
                         Model model) {
    LOGGER.info("saveUser()...");

    try {
      User user = userService.registerUser(userDTO, confirmPassword);
      LOGGER.info("User " + user.getUsername() + " saved successfully");
    } catch (Exception exception) {
      var errorMessage = exception.getMessage();
      LOGGER.info(errorMessage);
      model.addAttribute("messageError", errorMessage);
      return "signup";
    }
    return "redirect:/login";
  }
}
