package hr.dsteinh.edukacijskizadatak.repos.security;

import hr.dsteinh.edukacijskizadatak.model.security.FailureLogin;
import hr.dsteinh.edukacijskizadatak.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface FailureLoginRepo extends JpaRepository<FailureLogin, Long> {
    List<FailureLogin> findAllByUserAndCreatedDateIsAfter(User user, Timestamp timestamp);
}
