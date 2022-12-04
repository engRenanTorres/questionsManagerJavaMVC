/*
package br.com.engrenantorres.questionmanager.model;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.engrenantorres.questionmanager.repository.RoleRepository;
import br.com.engrenantorres.questionmanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  // test methods go here...
  @Test
  public void testAddRoleToNewUser() {
    Optional<Role> roleAdmin = roleRepository.findByName("ADM");

    User user = new User();
    user.setEmail("mikes.gates@gmail.com");
    user.setPassword("mike2020");
    user.setFirstName("Mike");
    user.setLastName("Gates");
    roleAdmin.ifPresent(roleAdm -> user.addRole(roleAdm));

    User savedUser = userRepository.save(user);

    assertThat(savedUser.getRoles().size()).isEqualTo(1);
  }

  @Test
  public void testAddRoleToExistingUser() {
    User user = userRepository.findByEmail("mikes.gates@gmail.com").get();
    Optional<Role> roleUser = roleRepository.findByName("User");
    Role roleCustomer = new Role("MLK");

    user.addRole(roleUser.get());
    user.addRole(roleCustomer);

    User savedUser = userRepository.save(user);

    assertThat(savedUser.getRoles().size()).isEqualTo(2);
  }
}
*/
