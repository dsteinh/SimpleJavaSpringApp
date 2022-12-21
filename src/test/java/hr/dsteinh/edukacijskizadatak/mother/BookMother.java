package hr.dsteinh.edukacijskizadatak.mother;

import hr.dsteinh.edukacijskizadatak.model.product.Book;

public class BookMother {
    public static Book createBook() {

        return Book.builder()
                .id(1L)
                .isbn("12345678")
                .writer(WriterMother.createWriter())
                .publisher(PublisherMother.createPublisher()).build();
    }
}
