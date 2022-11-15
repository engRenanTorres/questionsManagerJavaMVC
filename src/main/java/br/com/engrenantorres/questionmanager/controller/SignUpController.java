package br.com.engrenantorres.questionmanager.controller;

import br.com.engrenantorres.questionmanager.config.WebSecurityConfig;
import br.com.engrenantorres.questionmanager.dto.UserDTO;
import br.com.engrenantorres.questionmanager.model.User;
import br.com.engrenantorres.questionmanager.repository.UserRepository;
import br.com.engrenantorres.questionmanager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/signup")
public class SignUpController {
  @Autowired
  private UserService userService;

  private final Logger LOGGER = LoggerFactory.getLogger(SignUpController.class);
  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public String form(Model model){
    LOGGER.info("form()...");
    model.addAttribute("userDTO",new UserDTO());
    return "signup";
  }
  @PostMapping
  public String saveUser(UserDTO userDTO,
                         String confirmPassword,
                         Model model) {
    System.out.println("senha" + userDTO.getPassword());
    System.out.println("confirma" + confirmPassword);
    LOGGER.info("saveUser()...");
    if (!userDTO.getPassword().equals(confirmPassword)) {
      var errorMessage = "Senhas não conferem...";
      model.addAttribute("messageError",errorMessage);
      LOGGER.error(errorMessage);
      return "signup";
    }

    Optional<User> optionalUser = userRepository.findByUsername(userDTO.getUsername());
    if (optionalUser.isPresent()) {
      var errorMessage = "Usuário já existe";
      model.addAttribute("messageError",errorMessage);
      LOGGER.error(errorMessage);
      return "signup";
    }
    System.out.println(optionalUser.isPresent());

    User newUser = userDTO.toUser();

    userService.registerUser(newUser);

    return "redirect:/login";
  }
}
