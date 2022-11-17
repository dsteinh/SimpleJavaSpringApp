package hr.dsteinh.edukacijskizadatak.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class Person extends BaseEntity{
    private String firstName;
    private String lastName;
    private String oib;
}
