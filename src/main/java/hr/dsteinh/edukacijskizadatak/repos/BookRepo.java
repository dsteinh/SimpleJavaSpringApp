package hr.dsteinh.edukacijskizadatak.repos;

import hr.dsteinh.edukacijskizadatak.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends JpaRepository<Book, Long> {
}
