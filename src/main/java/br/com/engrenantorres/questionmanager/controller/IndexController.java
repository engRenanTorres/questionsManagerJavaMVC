package br.com.engrenantorres.questionmanager.controller;

import br.com.engrenantorres.questionmanager.model.Question;
import br.com.engrenantorres.questionmanager.model.SubjectArea;
import br.com.engrenantorres.questionmanager.repository.QuestionRepository;
import br.com.engrenantorres.questionmanager.repository.SubjectAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/questions-list")
public class IndexController {
  @Autowired
  private QuestionRepository questionRepository;
  @Autowired
  private SubjectAreaRepository areaRepository;

  @GetMapping
  public String index(Model model) {

    List<Question> questions = questionRepository.findAll();
    List<SubjectArea> areas = areaRepository.findAll();


    model.addAttribute("questions",questions);
    model.addAttribute("areas",areas);

    return "index";
  }
}
