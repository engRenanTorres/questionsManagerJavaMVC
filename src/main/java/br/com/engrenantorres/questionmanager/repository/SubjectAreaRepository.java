package br.com.engrenantorres.questionmanager.repository;

import br.com.engrenantorres.questionmanager.model.SubjectArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectAreaRepository extends JpaRepository<SubjectArea, Long> {
  public List<SubjectArea> findAll();
  Optional<SubjectArea> findById(Long id);
}
