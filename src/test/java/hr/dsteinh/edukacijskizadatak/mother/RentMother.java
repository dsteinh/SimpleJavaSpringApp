package hr.dsteinh.edukacijskizadatak.mother;

import hr.dsteinh.edukacijskizadatak.model.Rent;

import java.time.LocalDateTime;

public class RentMother {
    public static Rent createRent() {

        return new Rent(
                1L,
                BookMother.createBook(),
                UserMother.createUser(),
                20.5,
                LocalDateTime.now(),
                LocalDateTime.now());
    }
}
