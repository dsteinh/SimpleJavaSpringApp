package hr.dsteinh.edukacijskizadatak.model;

import hr.dsteinh.edukacijskizadatak.model.legal_entity.person.User;
import hr.dsteinh.edukacijskizadatak.model.product.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Book book = new Book();
    @ManyToOne
    private User user = new User();

    private double totalAmount;

    @DateTimeFormat(fallbackPatterns = {"M/d/yy", "dd.MM.yyyy"})
    private Date rentDate;
    @DateTimeFormat(fallbackPatterns = {"M/d/yy", "dd.MM.yyyy"})
    private Date returnDate;


}
