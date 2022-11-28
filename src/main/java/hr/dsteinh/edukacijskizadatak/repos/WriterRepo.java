package hr.dsteinh.edukacijskizadatak.repos;

import hr.dsteinh.edukacijskizadatak.model.legal_entity.person.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriterRepo extends JpaRepository<Writer, Long> {
}
