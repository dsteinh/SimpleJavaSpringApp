package hr.dsteinh.edukacijskizadatak.mother;

import hr.dsteinh.edukacijskizadatak.model.security.User;

public class UserMother {
    public static User createUser() {
        return User.builder()
                .id(1L)
                .firstName("Domagoj")
                .lastName("Stein")
                .oib("34563456").build();
    }
}
