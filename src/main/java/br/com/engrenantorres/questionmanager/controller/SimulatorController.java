package br.com.engrenantorres.questionmanager.controller;

import br.com.engrenantorres.questionmanager.model.Banca;
import br.com.engrenantorres.questionmanager.model.Question;
import br.com.engrenantorres.questionmanager.model.SubjectArea;
import br.com.engrenantorres.questionmanager.repository.BancaRepository;
import br.com.engrenantorres.questionmanager.repository.QuestionRepository;
import br.com.engrenantorres.questionmanager.repository.SubjectAreaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/simulator")
public class SimulatorController {

  private final Logger LOGGER = LoggerFactory.getLogger(SimulatorController.class);


  @GetMapping("init")
  public String getSimulator(
    @RequestParam(name="areaId",required = true,defaultValue = "0") Long areaId,
    Model model,
    Principal principal
  ) {
    LOGGER.info("getSimulator()...");

    model.addAttribute("userName", principal.getName());


    return "simulator/simulator";
  }



}
