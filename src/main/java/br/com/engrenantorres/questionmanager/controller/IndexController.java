package br.com.engrenantorres.questionmanager.controller;

import br.com.engrenantorres.questionmanager.model.Question;
import br.com.engrenantorres.questionmanager.model.SubjectArea;
import br.com.engrenantorres.questionmanager.repository.QuestionRepository;
import br.com.engrenantorres.questionmanager.repository.SubjectAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;
import java.util.Optional;

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
  @GetMapping("/{areaId}")
  public String filter(@PathVariable("areaId") Long areaId ,  Model model) {
    Optional<SubjectArea> optionalAreas = areaRepository.findById(areaId);

    SubjectArea area = new SubjectArea();

    if(optionalAreas.isPresent()) {
      area = optionalAreas.get();
      List<Question> questions = questionRepository.findByCargo(area);
      model.addAttribute("questions",questions);
    }



    return "index";
  }
  @ExceptionHandler(IllegalArgumentException.class)
  public String onError(){
    return "redirect:/";
  }
}
