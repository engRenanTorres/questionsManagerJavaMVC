package br.com.engrenantorres.questionmanager.repository;

import br.com.engrenantorres.questionmanager.model.Assunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssuntoRepository extends JpaRepository<Assunto,Long> {
}
