package hr.dsteinh.edukacijskizadatak.model.legal_entity.person;


import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Entity(name = "user_table")
@NoArgsConstructor
public class User extends Person{
    public User(String fName, String lName, String oib) {
        super.setFirstName(fName);
        super.setLastName(lName);
        super.setOib(oib);
    }

}