package hr.dsteinh.edukacijskizadatak.model.legal_entity.person;


import com.fasterxml.jackson.annotation.JsonIgnore;
import hr.dsteinh.edukacijskizadatak.model.Rent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity(name = "user_table")
@NoArgsConstructor
@Getter
@Setter
public class User extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public User(String fName, String lName, String oib) {
        super.setFirstName(fName);
        super.setLastName(lName);
        super.setOib(oib);
    }
    @JsonIgnore
    @ManyToOne
    private Rent rent;
}
