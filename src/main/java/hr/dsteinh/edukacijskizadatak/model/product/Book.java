package hr.dsteinh.edukacijskizadatak.model.product;

import hr.dsteinh.edukacijskizadatak.model.legal_entity.Publisher;
import hr.dsteinh.edukacijskizadatak.model.legal_entity.person.Writer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Book extends Product{
    private String isbn;
    @ManyToOne
    private Writer writer;

    @ManyToOne
    private Publisher publisher;
}
