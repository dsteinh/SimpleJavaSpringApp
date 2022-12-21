package hr.dsteinh.edukacijskizadatak.mother;

import hr.dsteinh.edukacijskizadatak.model.legal_entity.person.User;

public class UserMother {
    public static User createUser() {
        return new User(1L, "Domagoj", "Stein", "34563456");
    }
}
