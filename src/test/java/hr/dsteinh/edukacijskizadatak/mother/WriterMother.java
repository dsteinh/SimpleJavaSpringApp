package hr.dsteinh.edukacijskizadatak.mother;

import hr.dsteinh.edukacijskizadatak.model.Writer;

public class WriterMother {
    public static Writer createWriter(){
        return  Writer.builder()
                .id(1L)
                .firstName("Uncle")
                .lastName("Bob")
                .oib("1231234").build();
    }
}
