/*package br.com.engrenantorres.questionmanager.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import br.com.engrenantorres.questionmanager.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {

  @Autowired
  private RoleRepository roleRepository;

  @Test
  public void testCreateRoles() {
    Role user = new Role("User");
    Role admin = new Role("Admin");
    Role customer = new Role("Customer");

    roleRepository.saveAll(List.of(user, admin, customer));

    List<Role> listRoles = roleRepository.findAll();

    assertThat(listRoles.size()).isEqualTo(3);
  }



}*/
