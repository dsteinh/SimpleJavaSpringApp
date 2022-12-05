package hr.dsteinh.edukacijskizadatak.controllers;

import hr.dsteinh.edukacijskizadatak.model.legal_entity.person.Writer;
import hr.dsteinh.edukacijskizadatak.service.WriterService;
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

class WriterControllerShould {


    @Mock
    WriterService writerService;

    WriterController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new WriterController(writerService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void findAllWriters() throws Exception {
        Writer writer = new Writer();
        writer.setId(1L);

        List<Writer> writers = new ArrayList<>();
        writers.add(writer);

        when(writerService.findAll()).thenReturn(writers);

        mockMvc.perform(get("/api/writer"))
                .andExpect(status().isOk());
    }

    @Test
    void saveWriter() throws Exception {
        mockMvc.perform(post("/api/writer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                   "id": 0,
                                   "oib": "string",
                                   "firstName": "string",
                                   "lastName": "string"
                                 }"""))
                .andExpect(status().isOk());
    }

    @Test
    void findWriterById() throws Exception {
        Writer writer = new Writer();
        writer.setId(1L);

        when(writerService.findById(1L)).thenReturn(Optional.of(writer));

        mockMvc.perform(get("/api/writer/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteWriterById() throws Exception {

        mockMvc.perform(delete("/api/writer/2"))
                .andExpect(status().isOk());

    }
}