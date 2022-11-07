package br.com.engrenantorres.questionmanager.api;

import br.com.engrenantorres.questionmanager.model.Question;
import br.com.engrenantorres.questionmanager.repository.QuestionRepository;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/questions")
public class QuesitonsRest {
  @Autowired
  private QuestionRepository questionRepository;

  @GetMapping("all")
  public List<Question> getAllQuestions() {
    List<Question> all = questionRepository.findAll();
    return all;
  }

}
/*
  public Question randomQuestion() {
    Long qty = questionRepository.countAll();
    int idx = (int)(Math.random() * qty);
    Page<Question> questionPage = questionRepository.findAll(new PageRequest(idx, 1));
    Question q = null;
    if (questionPage.hasContent()) {
      q = questionPage.getContent().get(0);
    }
    return q;
  }*/
