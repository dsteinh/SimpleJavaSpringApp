package hr.dsteinh.edukacijskizadatak.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Writer extends Person{
    @OneToMany(mappedBy = "writer")
    private List<Book> books = new ArrayList();
}
