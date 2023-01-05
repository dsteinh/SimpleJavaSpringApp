package hr.dsteinh.edukacijskizadatak.model.legal_entity.person;


import com.fasterxml.jackson.annotation.JsonIgnore;
import hr.dsteinh.edukacijskizadatak.model.Rent;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "user_table")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class User extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Rent> rents = new ArrayList<>();

    @JsonIgnore
    private String username;
    @JsonIgnore
    private String password;

    public User(Long id,String fName, String lName, String oib) {
        this.id = id;
        super.setFirstName(fName);
        super.setLastName(lName);
        super.setOib(oib);
    }
}
