package br.com.engrenantorres.questionmanager.api;

import br.com.engrenantorres.questionmanager.model.Question;
import br.com.engrenantorres.questionmanager.model.SubjectArea;
import br.com.engrenantorres.questionmanager.repository.QuestionRepository;
import br.com.engrenantorres.questionmanager.repository.SubjectAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/questions")
public class QuesitonsRest {
  @Autowired
  private QuestionRepository questionRepository;
  @Autowired
  private SubjectAreaRepository subjectAreaRepository;

  @GetMapping("all/{areaId}")
  public ResponseEntity<List<Question>> getAllQuestionsByArea(
      @PathVariable("areaId") Long areaId,
      @RequestParam(name = "limit",required = false,defaultValue = "1") Integer limit
  ) {

    Optional<List<Question>> all = questionRepository.findAllBySubjectAreaId(areaId);

    if(all.isPresent()) {
      List<Question> groupOfQuestion = Arrays.asList(all.get().get(0),all.get().get(1));
      return ResponseEntity.ok(groupOfQuestion);
    }
    return ResponseEntity.notFound().build();
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
