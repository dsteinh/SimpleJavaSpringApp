package hr.dsteinh.edukacijskizadatak.repos;

import hr.dsteinh.edukacijskizadatak.model.legal_entity.person.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterRepo extends JpaRepository<Writer, Long> {
}
