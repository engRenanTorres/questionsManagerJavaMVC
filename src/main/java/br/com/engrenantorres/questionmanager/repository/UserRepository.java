package br.com.engrenantorres.questionmanager.repository;

import br.com.engrenantorres.questionmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
  User findByUsername(String username);
}
