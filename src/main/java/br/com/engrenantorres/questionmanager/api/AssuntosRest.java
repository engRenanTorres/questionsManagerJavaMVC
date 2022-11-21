package br.com.engrenantorres.questionmanager.api;

import br.com.engrenantorres.questionmanager.model.Assunto;
import br.com.engrenantorres.questionmanager.model.Question;
import br.com.engrenantorres.questionmanager.repository.AssuntoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("api/assuntos")
public class AssuntosRest {
  @Autowired
  AssuntoRepository assuntoRepository;

  @GetMapping("/{id}")
  public List<Assunto> getAllQuestions(
      @PathVariable("id") Long assuntoId
  ) {
    List<Assunto> all = assuntoRepository.findAllByCargoId(assuntoId);
    return all;
  }
}
