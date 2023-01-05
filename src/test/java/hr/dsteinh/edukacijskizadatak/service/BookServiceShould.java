package hr.dsteinh.edukacijskizadatak.service;

import hr.dsteinh.edukacijskizadatak.model.legal_entity.Publisher;
import hr.dsteinh.edukacijskizadatak.model.legal_entity.person.Writer;
import hr.dsteinh.edukacijskizadatak.model.product.Book;
import hr.dsteinh.edukacijskizadatak.repos.BookRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class BookServiceShould {
    @Autowired
    private BookService bookService;
    @MockBean
    private BookRepo bookRepo;

    @Test
    void findAllBooks() {
        //given
        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Book());

        given(bookRepo.findAll()).willReturn(expectedBooks);

        // When
        List<Book> actualBooks = bookService.findAll();

        // Then
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    void saveBook() {
        long id = 1L;
        Book expectedBook = Book.builder().id(id).build();

        given(bookRepo.save(expectedBook)).willReturn(expectedBook);

        // When
        Book actualBook = bookService.save(expectedBook);

        // Then
        assertEquals(expectedBook, actualBook);
    }

    @Test
    void findBookById() {
        // Given
        long id = 1L;
        Book expectedBook = new Book(id, "123", new Writer(), new Publisher(), new ArrayList<>());

        given(bookRepo.findById(id)).willReturn(Optional.of(expectedBook));

        // When
        Book actualBook = bookService.findById(id).get();

        // Then
        assertEquals(expectedBook, actualBook);
}

    @Test
    void deleteBookById() {
        //given
        long id = 1L;

        // When
        bookService.deleteById(id);

        // Then
        verify(bookRepo, times(1)).deleteById(id);
    }

    @Test
    void findBookByIsbn() throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/resources/service/test_book.json"));
        String bookJsonString = new String(bytes)
                .replace("\n", "")
                .replace("\r", "")
                .replace(" ", "");

        String isbn = "9781617294136";

        ResponseEntity<String> actualResponse = bookService.findByIsbn(isbn);

        assertEquals(bookJsonString, actualResponse.getBody().replace(" ", ""));
    }
}