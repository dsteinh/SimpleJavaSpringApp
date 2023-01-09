package hr.dsteinh.edukacijskizadatak.repos;

import hr.dsteinh.edukacijskizadatak.model.legal_entity.person.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
}
