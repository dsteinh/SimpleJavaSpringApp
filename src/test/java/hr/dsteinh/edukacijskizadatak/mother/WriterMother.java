package hr.dsteinh.edukacijskizadatak.mother;

import hr.dsteinh.edukacijskizadatak.model.legal_entity.person.Writer;

public class WriterMother {
    public static Writer createWriter(){
        return new Writer(1L, "uncle", "Bob", "1231234");
    }
}
