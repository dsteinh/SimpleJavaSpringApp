package hr.dsteinh.edukacijskizadatak.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Getter
@Setter
@Entity
public class Book extends Product{
    private String isbn;
    @ManyToOne
    private Writer writer;
}
