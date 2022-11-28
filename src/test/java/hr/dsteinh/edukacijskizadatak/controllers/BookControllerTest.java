package hr.dsteinh.edukacijskizadatak.controllers;

import hr.dsteinh.edukacijskizadatak.model.product.Book;
import hr.dsteinh.edukacijskizadatak.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookControllerTest {

    @Mock
    BookService bookService;

    BookController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new BookController(bookService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void findAll() throws Exception {
        Book book = new Book();
        book.setId(1L);

        List<Book> books = new ArrayList<>();
        books.add(book);

        when(bookService.findAll()).thenReturn(books);

        mockMvc.perform(get("/api/book"))
                .andExpect(status().isOk());
    }

    @Test
    void createBook() throws Exception {
        mockMvc.perform(post("/api/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "string",
                                  "price": 0,
                                  "quantity": 0,
                                  "description": "string",
                                  "isbn": "string",
                                  "writer": {
                                    "id": 4
                                 \s
                                  },
                                  "publisher": {
                                    "id": 7
                                \s
                                  }
                                }"""))
                .andExpect(status().isOk());
    }

    @Test
    void updateBook() throws Exception {
        Book book = new Book();
        book.setId(1L);

        when(bookService.createOrUpdate(book)).thenReturn(any(Book.class));

        mockMvc.perform(post("/api/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "id": 0,
                                  "name": "string",
                                  "price": 0,
                                  "quantity": 0,
                                  "description": "string",
                                  "isbn": "string",
                                  "writer": {
                                    "id": 4
                                 \s
                                  },
                                  "publisher": {
                                    "id": 7
                                \s
                                  }
                                }"""))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        Book book = new Book();
        book.setId(1L);

        when(bookService.findById(1L)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/api/book/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteById() throws Exception {

        mockMvc.perform(delete("/api/book/2"))
                .andExpect(status().isOk());

    }
}