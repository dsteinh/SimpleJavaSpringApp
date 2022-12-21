package hr.dsteinh.edukacijskizadatak.mother;

import hr.dsteinh.edukacijskizadatak.model.legal_entity.Publisher;

public class PublisherMother {
    public static Publisher createPublisher(){
        return new Publisher(1L, "knjiga.hr", "0987654");
    }
}
