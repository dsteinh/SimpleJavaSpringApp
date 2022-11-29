package hr.dsteinh.edukacijskizadatak.model.legal_entity.person;

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
public class Writer extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "writer")
    private List<Book> books = new ArrayList<>();

    public Writer(String fName, String lName, String oib) {
        super.setFirstName(fName);
        super.setLastName(lName);
        super.setOib(oib);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
