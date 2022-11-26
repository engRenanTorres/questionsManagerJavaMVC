package br.com.engrenantorres.questionmanager.service;

import br.com.engrenantorres.questionmanager.controller.QuestionsListController;
import br.com.engrenantorres.questionmanager.dto.NewQuestionDTO;
import br.com.engrenantorres.questionmanager.model.Assunto;
import br.com.engrenantorres.questionmanager.model.Banca;
import br.com.engrenantorres.questionmanager.model.Question;
import br.com.engrenantorres.questionmanager.model.SubjectArea;
import br.com.engrenantorres.questionmanager.model.enums.Cargo;
import br.com.engrenantorres.questionmanager.repository.AssuntoRepository;
import br.com.engrenantorres.questionmanager.repository.BancaRepository;
import br.com.engrenantorres.questionmanager.repository.QuestionRepository;
import br.com.engrenantorres.questionmanager.repository.SubjectAreaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionListService {

  private final Logger LOGGER = LoggerFactory.getLogger(QuestionsListController.class);

  private SubjectAreaRepository areaRepository;
  private BancaRepository bancaRepository;
  private QuestionRepository questionRepository;
  private AssuntoRepository assuntoRepository;
  private Integer PAG_SIZE = 5;

  @Autowired
  public QuestionListService(SubjectAreaRepository areaRepository,
                             BancaRepository bancaRepository,
                             QuestionRepository questionRepository,
                             AssuntoRepository assuntoRepository) {
    this.areaRepository = areaRepository;
    this.bancaRepository = bancaRepository;
    this.questionRepository = questionRepository;
    this.assuntoRepository = assuntoRepository;
  }

  public void injectBancaAndAreaAttsFromBD(Model model) {
    List<SubjectArea> areas = areaRepository.findAll();
    List<Banca> bancas = bancaRepository.findAll();
    model.addAttribute("areas", areas);
    model.addAttribute("cargos", Cargo.values());
    model.addAttribute("bancas", bancas);
  }

  public void HandleAttributes(Model model, Integer page, Long areaId) {
    Page<Question> questions;
    questions = getQuestionsApplyingFilterIfExist(model, page, areaId);

    Long pageMax = ((questions.getTotalElements() - 1) / PAG_SIZE);
    page = (page > pageMax.intValue()) ? pageMax.intValue() : page;
    injectQuestionsAttributes(model, page, questions);
  }
  private Page<Question> getQuestionsApplyingFilterIfExist(Model model, Integer page, Long areaId) {
    Page<Question> questions;
    if (areaId == 0) {
      model.addAttribute("areaId", "nulo");
      questions = questionRepository.findAll(PageRequest.of(page, PAG_SIZE));
    } else {
      model.addAttribute("areaId", areaId);
      Optional<SubjectArea> area = areaRepository.findById(areaId);
      questions = questionRepository.findBySubjectAreaOrderByDateAsc(area.get(), PageRequest.of(page, PAG_SIZE));
    }
    return questions;
  }

  public void getMyQuestionsList(Model model, Integer page, String username) {
    Page<Question> questions;
    Sort sort = Sort.by("subjectArea").ascending()
        .and(Sort.by("date").descending());
    questions = questionRepository.findAllByAuthor(username,PageRequest.of(page, PAG_SIZE, sort));

    injectQuestionsAttributes(model, page, questions);
  }


  public void injectQuestionsAttributes(Model model, Integer page, Page<Question> questions) {

    Integer nextPage = (page >= ((questions.getTotalElements() - 1) / PAG_SIZE)) ? page : page + 1;
    Integer previousPage = (page <= 0) ? 0 : page - 1;
    model.addAttribute("questions", questions);
    model.addAttribute("nextPage", nextPage);
    model.addAttribute("page",page);
    model.addAttribute("previousPage", previousPage);
    model.addAttribute("isFirst", questions.isFirst());
    model.addAttribute("isLast", questions.isLast());
  }

  public void injectQuestionsDataIntoEditForm(Model model, Optional<Question> question) {
    LOGGER.error("Illegal Argument error...");
    question.ifPresent(question1 -> {
      NewQuestionDTO questionDTO = new NewQuestionDTO(question1);
      List<Assunto> assuntos = assuntoRepository.findAllBySubjectAreaId(questionDTO.getSubjectArea().getId());

      model.addAttribute("newQuestionDTO", questionDTO);
      model.addAttribute("assuntos", assuntos);
    });
  }

  public void getToEdit(Model model, Long questionId) {
    Optional<Question> question = questionRepository.findById(questionId);
    injectQuestionsDataIntoEditForm(model, question);
    injectBancaAndAreaAttsFromBD(model);
  }

  public void delete(Long questionId, Model model, Integer page) {
    questionRepository.deleteById(questionId);
    HandleAttributes(model, page, 0L);
  }
}
