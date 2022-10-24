package br.com.engrenantorres.questionmanager.controller;

import br.com.engrenantorres.questionmanager.dto.NewQuestionDTO;
import br.com.engrenantorres.questionmanager.model.Banca;
import br.com.engrenantorres.questionmanager.model.SubjectArea;
import br.com.engrenantorres.questionmanager.repository.BancaRepository;
import br.com.engrenantorres.questionmanager.repository.QuestionRepository;
import br.com.engrenantorres.questionmanager.repository.SubjectAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/question-form")
public class QuestionFormController {
  @Autowired
  private QuestionRepository questionRepository;
  @Autowired
  BancaRepository bancaRepository;
  @Autowired
  SubjectAreaRepository subjectAreaRepository;

  @GetMapping
  public String form(Model model) {

    List<SubjectArea> areas = subjectAreaRepository.findAll();
    List<Banca> bancas = bancaRepository.findAll();

    model.addAttribute("areas",areas);
    model.addAttribute("bancas",bancas);
    return "question-form";
  }
  @PostMapping
  public String insert(NewQuestionDTO newQuestion){
    return "question-form";
  }
}
