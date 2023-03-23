package hr.dsteinh.edukacijskizadatak.repos.security;

import hr.dsteinh.edukacijskizadatak.model.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepo extends JpaRepository<Authority, Integer> {
}
