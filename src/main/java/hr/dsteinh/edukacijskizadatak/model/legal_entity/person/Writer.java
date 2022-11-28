package hr.dsteinh.edukacijskizadatak.model.legal_entity.person;

import hr.dsteinh.edukacijskizadatak.model.product.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Writer extends Person {
    @OneToMany(mappedBy = "writer")
    private List<Book> books = new ArrayList<>();

    public Writer(String fName, String lName, String oib) {
        super.setFirstName(fName);
        super.setLastName(lName);
        super.setOib(oib);
    }
}
