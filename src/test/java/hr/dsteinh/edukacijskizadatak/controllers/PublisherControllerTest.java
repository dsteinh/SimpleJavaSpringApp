package hr.dsteinh.edukacijskizadatak.controllers;

import hr.dsteinh.edukacijskizadatak.model.legal_entity.Publisher;
import hr.dsteinh.edukacijskizadatak.service.PublisherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PublisherControllerTest {

    @Mock
    PublisherService publisherService;

    PublisherController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new PublisherController(publisherService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void findAll() throws Exception {
        Publisher publisher = new Publisher();
        publisher.setId(1L);

        List<Publisher> publishers = new ArrayList<>();
        publishers.add(publisher);

        when(publisherService.findAll()).thenReturn(publishers);

        mockMvc.perform(get("/api/publisher"))
                .andExpect(status().isOk());
    }

    @Test
    void save() throws Exception {
        mockMvc.perform(post("/api/publisher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                     "id": 0,
                                     "oib": "string",
                                     "name": "string"
                                   }"""))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        Publisher publisher = new Publisher();
        publisher.setId(1L);

        when(publisherService.findById(1L)).thenReturn(Optional.of(publisher));

        mockMvc.perform(get("/api/publisher/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete("/api/publisher/2"))
                .andExpect(status().isOk());
    }
}