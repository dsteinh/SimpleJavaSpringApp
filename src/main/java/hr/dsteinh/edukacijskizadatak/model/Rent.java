package hr.dsteinh.edukacijskizadatak.model;

import hr.dsteinh.edukacijskizadatak.model.product.Book;
import hr.dsteinh.edukacijskizadatak.model.security.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

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

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp rentDate;

    @UpdateTimestamp
    private Timestamp returnDate;

    public void setBook(Book book) {
        this.book = book;
        book.getRents().add(this);
    }

    public void setUser(User user) {
        this.user = user;
        user.getRents().add(this);
    }

}
