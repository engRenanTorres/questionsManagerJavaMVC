package br.com.engrenantorres.questionmanager.service;


import br.com.engrenantorres.questionmanager.controller.SignUpController;
import br.com.engrenantorres.questionmanager.dto.UserDTO;
import br.com.engrenantorres.questionmanager.model.Role;
import br.com.engrenantorres.questionmanager.model.User;
import br.com.engrenantorres.questionmanager.repository.RoleRepository;
import br.com.engrenantorres.questionmanager.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
  private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;
  private RoleRepository roleRepository;

  @Autowired
  public UserService(UserRepository userRepository,
                     RoleRepository roleRepository,
                     @Lazy PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.roleRepository = roleRepository;
  }
  public String registerUser(UserDTO userDTO, String confirmPassword, Model model){

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
    Optional<Role> roleOptional = roleRepository.findByName("ROLE_USER");
    User newUser = userDTO.toUser();
    roleOptional.ifPresent(role -> newUser.addRole(role));
    newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
    userRepository.save(newUser);
    LOGGER.info("Saved new user successfully. Username = " + newUser.getUsername());
    return "redirect:/login";
  }

  public Void registerUser(User newUser){
    Optional<User> optionalUser = userRepository.findByUsername(newUser.getUsername());
    if (optionalUser.isPresent()) {
      var errorMessage = "Usuário já existe";
      LOGGER.error(errorMessage);
    }
    Optional<Role> roleOptional = roleRepository.findByName("ROLE_USER");
    roleOptional.ifPresent(role -> newUser.addRole(role));
    newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
    userRepository.save(newUser);
    LOGGER.info("Saved new user successfully. Username = " + newUser.getUsername());
    return null;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByUsername(username);

    if(user.isEmpty()) throw new UsernameNotFoundException("Usuário não cadastrado");

    LOGGER.info("Logged as "+ username);

    /*return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
        user.get().getPassword(), Collections.emptyList());*/
    return user.get();
  }
  public static void main(String[] args) {

    System.out.println();
  }
}
