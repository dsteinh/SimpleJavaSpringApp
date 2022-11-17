package hr.dsteinh.edukacijskizadatak.repos;

import hr.dsteinh.edukacijskizadatak.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
