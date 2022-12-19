package hr.dsteinh.edukacijskizadatak.controllers;

import hr.dsteinh.edukacijskizadatak.model.product.Book;
import hr.dsteinh.edukacijskizadatak.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @PostMapping
    public Book save(@Validated @RequestBody Book book) {
        return bookService.save(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable(value = "id") long id) {
        Optional<Book> book = bookService.findById(id);
        return book.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable(value = "id") long id) {
        if (bookService.findById(id).isEmpty()) {
            return HttpStatus.NOT_FOUND;
        }
        bookService.deleteById(id);
        return HttpStatus.OK;
    }

    @GetMapping("/search/{isbn}")
    public ResponseEntity<String> findByIsbn(@PathVariable(name = "isbn") String isbn) {
        return bookService.findByIsbn(isbn);
    }
}
