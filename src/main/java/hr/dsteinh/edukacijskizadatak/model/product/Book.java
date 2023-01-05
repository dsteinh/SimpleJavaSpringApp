package hr.dsteinh.edukacijskizadatak.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hr.dsteinh.edukacijskizadatak.model.Rent;
import hr.dsteinh.edukacijskizadatak.model.legal_entity.Publisher;
import hr.dsteinh.edukacijskizadatak.model.legal_entity.person.Writer;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String isbn;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Writer writer;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Publisher publisher;

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private List<Rent> rents = new ArrayList<>();

    public void setWriter(@NotNull Writer writer) {
        this.writer = writer;
        writer.getBooks().add(this);
    }
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
        publisher.getBooks().add(this);
    }

}
