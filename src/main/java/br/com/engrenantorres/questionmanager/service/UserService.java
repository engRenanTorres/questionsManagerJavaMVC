package br.com.engrenantorres.questionmanager.service;


import br.com.engrenantorres.questionmanager.controller.SignUpController;
import br.com.engrenantorres.questionmanager.dto.UserDTO;
import br.com.engrenantorres.questionmanager.model.Role;
import br.com.engrenantorres.questionmanager.model.User;
import br.com.engrenantorres.questionmanager.repository.RoleRepository;
import br.com.engrenantorres.questionmanager.repository.UserRepository;
import br.com.engrenantorres.questionmanager.util.CheckStrings;
import jdk.jshell.spi.ExecutionControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
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
  public User registerUser(UserDTO userDTO, String confirmPassword) throws Exception {

    userValidations(userDTO, confirmPassword);

    Optional<Role> roleOptional = roleRepository.findByName("ROLE_USER");
    User newUser = userDTO.toUser();
    roleOptional.ifPresent(role -> newUser.addRole(role));
    newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
    User user = userRepository.save(newUser);
    LOGGER.info("Saved new user successfully. Username = " + newUser.getUsername());
    return user;
  }

  private void userValidations(UserDTO userDTO, String confirmPassword) throws Exception {
    if (!userDTO.getPassword().equals(confirmPassword)) {
      var errorMessage = "Senhas não conferem...";
      LOGGER.error(errorMessage);
      throw new Exception(errorMessage);
    }
    checkIfUserExistsByUserNameAndEmail(userDTO.toUser());
  }

  public Void registerUser(User newUser) throws Exception {

    checkIfUserExistsByUserNameAndEmail(newUser);

    Optional<Role> roleOptional = roleRepository.findByName("ROLE_USER");
    roleOptional.ifPresent(role -> newUser.addRole(role));
    newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
    userRepository.save(newUser);
    LOGGER.info("Saved new user successfully. Username = " + newUser.getUsername());
    return null;
  }

  private void checkIfUserExistsByUserNameAndEmail(User newUser) throws Exception {
    Optional<User> optionalUser = userRepository.findByUsername(newUser.getUsername());
    if (optionalUser.isPresent()) {
      var errorMessage = "Login já existe";
      LOGGER.error(errorMessage);
      throw new Exception(errorMessage);
    }
    optionalUser = userRepository.findByEmail(newUser.getEmail());
    if (optionalUser.isPresent()) {
      var errorMessage = "E-mail já cadastrado";
      LOGGER.error(errorMessage);
      throw new Exception(errorMessage);
    }
  }

  //function used to login
  @Override
  public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
    Optional<User> user = getUserFromUsernameOrEmail(usernameOrEmail);

    if(user.isEmpty()) throw new UsernameNotFoundException("Usuário não cadastrado");

    LOGGER.info("Logged as "+ user.get().getUsername());

    /*return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
        user.get().getPassword(), Collections.emptyList());*/
    return user.get();
  }

  public void checkAndHandleUserLogged(Model model) {
    if(SecurityContextHolder.getContext().getAuthentication().getName() != null) {
      String username = SecurityContextHolder.getContext().getAuthentication().getName();
      model.addAttribute("userName", username);
    }
  }

  private Optional<User> getUserFromUsernameOrEmail(String usernameOrEmail) {
    Optional<User> user;
    if(CheckStrings.isEmail(usernameOrEmail)){
      user = userRepository.findByEmail(usernameOrEmail);
    } else {
      user = userRepository.findByUsername(usernameOrEmail);
    }
    return user;
  }

}
