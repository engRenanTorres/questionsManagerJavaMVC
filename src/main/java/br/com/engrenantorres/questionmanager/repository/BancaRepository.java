package br.com.engrenantorres.questionmanager.repository;

import br.com.engrenantorres.questionmanager.model.Banca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BancaRepository extends JpaRepository<Banca, Long> {
}
