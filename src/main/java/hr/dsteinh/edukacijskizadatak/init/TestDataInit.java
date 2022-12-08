
package hr.dsteinh.edukacijskizadatak.init;

import hr.dsteinh.edukacijskizadatak.model.legal_entity.Publisher;
import hr.dsteinh.edukacijskizadatak.model.legal_entity.person.User;
import hr.dsteinh.edukacijskizadatak.model.legal_entity.person.Writer;
import hr.dsteinh.edukacijskizadatak.model.product.Book;
import hr.dsteinh.edukacijskizadatak.service.BookService;
import hr.dsteinh.edukacijskizadatak.service.PublisherService;
import hr.dsteinh.edukacijskizadatak.service.UserService;
import hr.dsteinh.edukacijskizadatak.service.WriterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestDataInit implements CommandLineRunner {
    private final UserService userService;
    private final WriterService writerService;
    private final PublisherService publisherService;
    private final BookService bookService;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    public TestDataInit(UserService userService, WriterService writerService, PublisherService publisherService, BookService bookService) {
        this.userService = userService;
        this.writerService = writerService;
        this.publisherService = publisherService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) {
        loadMockUsers();
        loadMockWriters();
        loadMockPublishers();
        loadMockBooks();

    }

    private void loadMockBooks() {
        Book book = new Book();
        book.setIsbn("1111111111");
        book.setName("Song of Ice and Fire");
        book.setPublisher(publisherService.findAll().get(0));
        book.setWriter(writerService.findAll().get(0));
        book.setQuantity(12);
        book.setPrice(45.5);
        book.setDescription("Dragons!");


        bookService.save(book);

        Book book2 = new Book();
        book2.setIsbn("2222222222");
        book2.setName("Lord of the rings");
        book2.setPublisher(publisherService.findAll().get(1));
        book2.setWriter(writerService.findAll().get(1));
        book2.setQuantity(100);
        book2.setPrice(55.5);
        book2.setDescription("And my axe!");

        bookService.save(book2);

        Book book3 = new Book();
        book3.setIsbn("3333333333");
        book3.setName("Clean code");
        book3.setPublisher(publisherService.findAll().get(2));
        book3.setWriter(writerService.findAll().get(2));
        book3.setQuantity(90);
        book3.setPrice(66.6);
        book3.setDescription("Write better code!");

        bookService.save(book3);
    }

    private void loadMockPublishers() {
        Publisher pub1 = new Publisher("Knjiga d.o.o", "5632892");
        publisherService.save(pub1);
        Publisher pub2 = new Publisher("Objavim d.o.o", "798423098");
        publisherService.save(pub2);
        Publisher pub3 = new Publisher("Čitaj d.o.o", "08642135");
        publisherService.save(pub3);
    }

    private void loadMockWriters() {
        Writer george = new Writer("George R.R.", "Martin", "12345212");
        writerService.save(george);
        Writer tolken = new Writer("J. R. R.", "Tolken", "5834950743");
        writerService.save(tolken);
        Writer robert = new Writer("Robert Cecil", "Martin", "523148979");
        writerService.save(robert);


        System.out.println("Current database is " + activeProfile);
    }

    private void loadMockUsers() {
        User tony = new User("Tony", "Stark", "3000");
        userService.save(tony);
        User steve = new User("Steve", "Rogers", "9875311122");
        userService.save(steve);
        User milica = new User("Milica", "Krmpotić", "523148979");
        userService.save(milica);
    }
}


