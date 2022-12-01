package hr.dsteinh.edukacijskizadatak.model.legal_entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@MappedSuperclass
public abstract class LegalEntity {
    @NotBlank
    private String oib;

}


