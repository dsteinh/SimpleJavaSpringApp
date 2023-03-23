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
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String oib;

    @JsonIgnore
    @OneToMany(mappedBy = "publisher", fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();


    @Override
    public String toString() {
        return super.toString();
    }
}
