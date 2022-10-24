package br.com.engrenantorres.questionmanager.repository;

import br.com.engrenantorres.questionmanager.model.Question;
import br.com.engrenantorres.questionmanager.model.SubjectArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectAreaRepository extends JpaRepository<SubjectArea, Long> {
  public List<SubjectArea> findAll();
}
