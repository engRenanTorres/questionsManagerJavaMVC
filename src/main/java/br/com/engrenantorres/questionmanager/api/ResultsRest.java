package br.com.engrenantorres.questionmanager.api;

import br.com.engrenantorres.questionmanager.dto.ResultDTO;
import br.com.engrenantorres.questionmanager.model.Question;
import br.com.engrenantorres.questionmanager.model.Result;
import br.com.engrenantorres.questionmanager.model.User;
import br.com.engrenantorres.questionmanager.repository.QuestionRepository;
import br.com.engrenantorres.questionmanager.repository.ResultRepository;
import br.com.engrenantorres.questionmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/results")
public class ResultsRest {
  @Autowired
  private QuestionRepository questionRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private ResultRepository resultRepository;

  @PostMapping
  public Result createResult(@RequestBody ResultDTO request ){

    Optional<Question> questionFetch = questionRepository.findById(request.getQuestionId());
    if(!questionFetch.isPresent()) {
      return null;
    }

    Question question = questionFetch.get();

    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    Optional<User> user = userRepository.findByUsername(username);

    Result newResult = request.toResult(question,user.get());

    resultRepository.save(newResult);

    return newResult;

  }
}
