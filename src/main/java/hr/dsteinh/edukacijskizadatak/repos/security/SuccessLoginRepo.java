package hr.dsteinh.edukacijskizadatak.repos.security;

import hr.dsteinh.edukacijskizadatak.model.security.SuccessLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuccessLoginRepo extends JpaRepository<SuccessLogin, Long> {
}
