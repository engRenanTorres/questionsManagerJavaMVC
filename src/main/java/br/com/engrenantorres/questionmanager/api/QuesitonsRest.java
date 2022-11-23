package br.com.engrenantorres.questionmanager.api;

import br.com.engrenantorres.questionmanager.model.Question;
import br.com.engrenantorres.questionmanager.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/questions")
public class QuesitonsRest {
  @Autowired
  private QuestionRepository questionRepository;

  @GetMapping("all/{areaId}")
  public Stream<Question> getAllQuestionsByArea(
      @PathVariable("areaId") Long areaId,
      @RequestParam(name = "limit",required = false,defaultValue = "1") Integer limit
  ) {
    Set<Question> all = questionRepository.findAllBySubjectAreaId(areaId);
    Stream<Question> groupOfQuestion = all.stream().limit(2);
    return groupOfQuestion;
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
