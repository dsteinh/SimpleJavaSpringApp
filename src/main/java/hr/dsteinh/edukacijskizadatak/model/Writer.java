package hr.dsteinh.edukacijskizadatak.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hr.dsteinh.edukacijskizadatak.model.product.Book;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String oib;

    @JsonIgnore
    @OneToMany(mappedBy = "writer", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Book> books = new ArrayList<>();


    @Override
    public String toString() {
        return super.toString();
    }
}
