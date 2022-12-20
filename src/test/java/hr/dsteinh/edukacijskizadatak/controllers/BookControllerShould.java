package hr.dsteinh.edukacijskizadatak.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.dsteinh.edukacijskizadatak.model.legal_entity.Publisher;
import hr.dsteinh.edukacijskizadatak.model.legal_entity.person.Writer;
import hr.dsteinh.edukacijskizadatak.model.product.Book;
import hr.dsteinh.edukacijskizadatak.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerShould {

    public static final String TEST_BOOK_JSON = "src/test/resources/controller/test_book.json";

    public static final String API_BOOKS = "/api/books";
    @Autowired
    MockMvc mockMvc;
    @MockBean
    BookService bookService;

    private final ObjectMapper mapper = new ObjectMapper();


    @Test
    void findAllBooks() throws Exception {
        Book book = new Book();
        List<Book> books = new ArrayList<>();

        books.add(book);

        when(bookService.findAll()).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(API_BOOKS)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void saveBook() throws Exception {
        Book book = new Book(1L, "123", new Writer(), new Publisher(), new ArrayList<>());

        when(bookService.save(book)).thenReturn(book);

        mapper.writeValue(new File(TEST_BOOK_JSON), book);
        String jsonBookToString = mapper.writeValueAsString(book);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(API_BOOKS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBookToString))
                .andExpect(status().isOk());
    }

    @Test
    void findBookById() throws Exception {
        Book book = new Book(1L, "123", new Writer(), new Publisher(), new ArrayList<>());

        when(bookService.findById(1L)).thenReturn(Optional.of(book));

        mapper.writeValue(new File(TEST_BOOK_JSON), book);
        String jsonBookToString = mapper.writeValueAsString(book);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(API_BOOKS + "/1"))
                .andExpect(content().string(jsonBookToString))
                .andExpect(status().isOk());
    }

    @Test
    void deleteBookById() throws Exception {
        mockMvc.perform(delete(API_BOOKS + "/1"))
                .andExpect(status().isOk());
    }
}