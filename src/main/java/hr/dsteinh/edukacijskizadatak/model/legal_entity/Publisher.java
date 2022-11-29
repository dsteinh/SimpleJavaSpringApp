package hr.dsteinh.edukacijskizadatak.model.legal_entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hr.dsteinh.edukacijskizadatak.model.product.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Publisher extends LegalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "publisher", fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();

    public Publisher(String name, String oib) {
        super.setOib(oib);
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
