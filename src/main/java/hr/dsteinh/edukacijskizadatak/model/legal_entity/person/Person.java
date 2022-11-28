package hr.dsteinh.edukacijskizadatak.model.legal_entity.person;

import hr.dsteinh.edukacijskizadatak.model.legal_entity.LegalEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class Person extends LegalEntity {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

}
