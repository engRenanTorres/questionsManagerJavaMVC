package br.com.engrenantorres.questionmanager.repository;

import br.com.engrenantorres.questionmanager.model.Question;
import br.com.engrenantorres.questionmanager.model.SubjectArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

  List<Question> findAll();
  List<Question> findByCargo(SubjectArea areaSelected);

/*  --Para ter queries personalizadas-- Precisa criar uma Class
  @PersistenceContext
  private EntityManager entityManager;

  public List<Question> getAllQuestions() {
    Query query = entityManager.createQuery("from Question", Question.class);

    return query.getResultList();
  }*/
}
