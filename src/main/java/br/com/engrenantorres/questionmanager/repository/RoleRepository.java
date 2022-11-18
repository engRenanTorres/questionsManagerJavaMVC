package br.com.engrenantorres.questionmanager.repository;

import br.com.engrenantorres.questionmanager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
  Optional<Role> findByName(String name);
}
