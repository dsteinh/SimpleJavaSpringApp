package hr.dsteinh.edukacijskizadatak.model;

import hr.dsteinh.edukacijskizadatak.model.legal_entity.person.User;
import hr.dsteinh.edukacijskizadatak.model.product.Book;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Book book = new Book();
    @ManyToOne(cascade = CascadeType.MERGE)
    private User user = new User();

    private double totalAmount;

    private LocalDateTime rentDate;
    private LocalDateTime returnDate;

    public void setBook(Book book) {
        this.book = book;
        book.getRents().add(this);
    }

}
