package br.com.engrenantorres.questionmanager.repository;

import br.com.engrenantorres.questionmanager.model.AccessModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessModelRepository extends JpaRepository<AccessModel,Long> {
}
