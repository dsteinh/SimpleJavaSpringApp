package hr.dsteinh.edukacijskizadatak.model.legal_entity.person;


import com.fasterxml.jackson.annotation.JsonIgnore;
import hr.dsteinh.edukacijskizadatak.model.Rent;
import lombok.*;

import javax.persistence.*;


@Entity(name = "user_table")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class User extends Person {

    //public enum Role {USER, WORKER}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Rent rent;

    @JsonIgnore
    private String username;
    @JsonIgnore
    private String password;

    public User(String fName, String lName, String oib) {
        super.setFirstName(fName);
        super.setLastName(lName);
        super.setOib(oib);
    }
    public User(Long id,String fName, String lName, String oib) {
        this.id = id;
        super.setFirstName(fName);
        super.setLastName(lName);
        super.setOib(oib);
    }
}
