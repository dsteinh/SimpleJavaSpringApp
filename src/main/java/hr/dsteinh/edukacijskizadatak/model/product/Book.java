package hr.dsteinh.edukacijskizadatak.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hr.dsteinh.edukacijskizadatak.model.Rent;
import hr.dsteinh.edukacijskizadatak.model.legal_entity.Publisher;
import hr.dsteinh.edukacijskizadatak.model.legal_entity.person.Writer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Book extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String isbn;
    @ManyToOne
    private Writer writer;

    @ManyToOne
    private Publisher publisher;
    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<Rent> rents;
}
