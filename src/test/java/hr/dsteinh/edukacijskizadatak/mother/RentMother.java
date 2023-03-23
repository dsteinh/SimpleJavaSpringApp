package hr.dsteinh.edukacijskizadatak.mother;

import hr.dsteinh.edukacijskizadatak.model.Rent;

public class RentMother {
    public static Rent createRent() {
        return Rent.builder()
                .id(1L)
                .book(BookMother.createBook())
                .user(UserMother.createUser())
                .totalAmount(20.5).build();
    }
}
