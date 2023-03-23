package hr.dsteinh.edukacijskizadatak.service;

import hr.dsteinh.edukacijskizadatak.model.Publisher;
import hr.dsteinh.edukacijskizadatak.mother.PublisherMother;
import hr.dsteinh.edukacijskizadatak.repos.PublisherRepo;
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
class PublisherServiceShould {

    @Autowired
    private PublisherService publisherService;
    @MockBean
    private PublisherRepo publisherRepo;

    @Test
    void findAllPublishers() {
        //given
        List<Publisher> expectedPublishers = new ArrayList<>();
        expectedPublishers.add(PublisherMother.createPublisher());

        given(publisherRepo.findAll()).willReturn(expectedPublishers);
        // When

        List<Publisher> actualPublishers = publisherService.findAll();
        // Then

        assertEquals(expectedPublishers, actualPublishers);
    }

    @Test
    void savePublisher() {
        //given
        Publisher expectedPublisher = PublisherMother.createPublisher();

        given(publisherRepo.save(expectedPublisher)).willReturn(expectedPublisher);
        // When

        Publisher actualPublisher = publisherService.save(expectedPublisher);
        // Then

        assertEquals(expectedPublisher, actualPublisher);
    }

    @Test
    void findPublisherById() {
        // Given
        long id = 1L;
        Publisher expectedPublisher = PublisherMother.createPublisher();

        given(publisherRepo.findById(id)).willReturn(Optional.of(expectedPublisher));

        // When
        Publisher actualPublisher = publisherService.findById(id).get();

        // Then
        assertEquals(expectedPublisher, actualPublisher);
    }

    @Test
    void deletePublisherById() {
        long id = 1L;
        // When

        publisherService.deleteById(id);
        // Then

        verify(publisherRepo, times(1)).deleteById(id);
    }
}