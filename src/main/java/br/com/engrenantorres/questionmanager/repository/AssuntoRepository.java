package br.com.engrenantorres.questionmanager.repository;

import br.com.engrenantorres.questionmanager.model.Assunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AssuntoRepository extends JpaRepository<Assunto,Long> {
  @Query("select a from Assunto a where a.cargo.id = ?1")
  List<Assunto> findAllByCargoId(Long areaId);
}
