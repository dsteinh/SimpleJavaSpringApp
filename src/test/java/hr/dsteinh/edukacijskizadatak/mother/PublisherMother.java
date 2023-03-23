package hr.dsteinh.edukacijskizadatak.mother;

import hr.dsteinh.edukacijskizadatak.model.Publisher;

public class PublisherMother {
    public static Publisher createPublisher(){
        return Publisher.builder()
                .id(1L)
                .name("knjiga.hr")
                .oib("0987654123").build();
    }
}
