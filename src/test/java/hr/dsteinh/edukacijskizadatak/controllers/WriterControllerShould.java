package hr.dsteinh.edukacijskizadatak.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.dsteinh.edukacijskizadatak.config.SecurityConfig;
import hr.dsteinh.edukacijskizadatak.model.legal_entity.person.Writer;
import hr.dsteinh.edukacijskizadatak.mother.WriterMother;
import hr.dsteinh.edukacijskizadatak.security.JpaUserDetailsService;
import hr.dsteinh.edukacijskizadatak.service.WriterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WriterController.class)
@Import(SecurityConfig.class)
class WriterControllerShould {

    public static final String TEST_WRITER_JSON = "src/test/resources/controller/test_writer.json";
    private static final String API_WRITERS = "/api/writers";
    @Autowired
    MockMvc mockMvc;
    @MockBean
    WriterService writerService;

    @MockBean
    JpaUserDetailsService userDetailsService;
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void findAllWriters() throws Exception {
        Writer writer = WriterMother.createWriter();
        List<Writer> writers = new ArrayList<>();

        writers.add(writer);

        when(writerService.findAll()).thenReturn(writers);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(API_WRITERS)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveWriter() throws Exception {
        Writer writer = WriterMother.createWriter();

        when(writerService.save(writer)).thenReturn(writer);

        mapper.writeValue(new File(TEST_WRITER_JSON), writer);
        String jsonWriterToString = mapper.writeValueAsString(writer);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(API_WRITERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonWriterToString))
                .andExpect(status().isOk());
    }

    @Test
    void findWriterById() throws Exception {
        Writer writer = WriterMother.createWriter();

        when(writerService.findById(1L)).thenReturn(Optional.of(writer));

        mapper.writeValue(new File(TEST_WRITER_JSON), writer);
        String jsonWriterToString = mapper.writeValueAsString(writer);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(API_WRITERS + "/1"))
                .andExpect(content().string(jsonWriterToString))
                .andExpect(status().isOk());
    }
    @WithMockUser(authorities = "ADMIN")
    @Test
    void deleteWriterById() throws Exception {
        mockMvc.perform(delete(API_WRITERS + "/1"))
                .andExpect(status().isOk());
    }
    @WithMockUser
    @Test
    void notDeleteAndGetForbidden() throws Exception {
        mockMvc.perform(delete(API_WRITERS + "/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    void notDeleteAndGetUnauthorized() throws Exception {
        mockMvc.perform(delete(API_WRITERS + "/1"))
                .andExpect(status().isUnauthorized());
    }
}