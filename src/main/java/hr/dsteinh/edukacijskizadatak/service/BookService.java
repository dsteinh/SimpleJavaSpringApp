package hr.dsteinh.edukacijskizadatak.service;

import hr.dsteinh.edukacijskizadatak.repos.BookRepo;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepo bookRepo;


    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }
}
