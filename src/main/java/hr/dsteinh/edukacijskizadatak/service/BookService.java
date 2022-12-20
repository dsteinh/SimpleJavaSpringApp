package hr.dsteinh.edukacijskizadatak.service;

import hr.dsteinh.edukacijskizadatak.model.product.Book;
import hr.dsteinh.edukacijskizadatak.repos.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookRepo bookRepo;
    private final RestTemplate bookApiRestTemplate;

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public Book save(Book book) {
        return bookRepo.save(book);
    }

    public Optional<Book> findById(long id) {
        return bookRepo.findById(id);
    }

    public void deleteById(long id) {
        bookRepo.deleteById(id);
    }

    public ResponseEntity<String> findByIsbn(String isbn) {
        return bookApiRestTemplate.getForEntity(isbn, String.class);
    }
}
