package br.com.engrenantorres.questionmanager.repository;

import br.com.engrenantorres.questionmanager.model.Question;
import br.com.engrenantorres.questionmanager.model.SubjectArea;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

  List<Question> findAll();
  @Cacheable("questionsC")
  Page<Question> findByCargoOrderByDateAsc(SubjectArea areaSelected, Pageable pageable);
  @Query("SELECT q FROM Question q JOIN q.author a WHERE a.username = :username")
  Page<Question> findAllByAuthor(@Param("username") String username, Pageable pageable);

  @Query("SELECT q FROM Question q WHERE q.cargo.name = :cargoname")
  Page<Question> findAllByCargo(@Param("cargoname") String cargoname, Pageable pageable);

/*  --Para ter queries personalizadas-- Precisa criar uma Class
  @PersistenceContext
  private EntityManager entityManager;

  public List<Question> getAllQuestions() {
    Query query = entityManager.createQuery("from Question", Question.class);

    return query.getResultList();
  }*/
}
