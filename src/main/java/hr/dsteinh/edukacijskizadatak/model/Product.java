package hr.dsteinh.edukacijskizadatak.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class Product extends BaseEntity{
    private String name;
    private double price;
    private int quantity;
    private String description;
}