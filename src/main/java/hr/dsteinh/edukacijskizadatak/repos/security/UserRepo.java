package hr.dsteinh.edukacijskizadatak.repos.security;

import hr.dsteinh.edukacijskizadatak.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
}
