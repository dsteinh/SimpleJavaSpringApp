package hr.dsteinh.edukacijskizadatak.service;

import hr.dsteinh.edukacijskizadatak.model.Writer;
import hr.dsteinh.edukacijskizadatak.mother.WriterMother;
import hr.dsteinh.edukacijskizadatak.repos.WriterRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class WriterServiceShould {

    @Autowired
    private WriterService writerService;
    @MockBean
    private WriterRepo writerRepo;

    @Test
    void findAllWriters() {
        //given
        List<Writer> expectedWriters = new ArrayList<>();
        expectedWriters.add(WriterMother.createWriter());

        given(writerRepo.findAll()).willReturn(expectedWriters);

        // When
        List<Writer> actualWriters = writerService.findAll();

        // Then
        assertEquals(expectedWriters, actualWriters);
    }

    @Test
    void saveWriter() {
        Writer expectedWriter = WriterMother.createWriter();

        given(writerRepo.save(expectedWriter)).willReturn(expectedWriter);

        // When
        Writer actualWriter = writerService.save(expectedWriter);

        // Then
        assertEquals(expectedWriter, actualWriter);
    }

    @Test
    void findWriterById() {
        long id = 1L;
        Writer expectedWriter = WriterMother.createWriter();

        given(writerRepo.findById(id)).willReturn(Optional.of(expectedWriter));

        // When
        Writer actualWriter = writerService.findById(id).get();

        // Then
        assertEquals(expectedWriter, actualWriter);
    }

    @Test
    void deleteWriterById() {
        //given
        long id = 1L;

        // When
        writerService.deleteById(id);

        // Then
        verify(writerRepo, times(1)).deleteById(id);
    }
}