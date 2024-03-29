package hr.dsteinh.edukacijskizadatak.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.dsteinh.edukacijskizadatak.config.SecurityConfig;
import hr.dsteinh.edukacijskizadatak.model.Publisher;
import hr.dsteinh.edukacijskizadatak.mother.PublisherMother;
import hr.dsteinh.edukacijskizadatak.security.JpaUserDetailsService;
import hr.dsteinh.edukacijskizadatak.service.PublisherService;
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

@WebMvcTest(PublisherController.class)
@Import(SecurityConfig.class)
class PublisherControllerShould {

    public static final String TEST_PUBLISHER_JSON = "src/test/resources/controller/test_publisher.json";
    private static final String API_PUBLISHERS = "/api/publishers";
    @Autowired
    MockMvc mockMvc;
    @MockBean
    PublisherService publisherService;

    @MockBean
    JpaUserDetailsService userDetailsService;
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void findAllPublishers() throws Exception {
        Publisher publisher = PublisherMother.createPublisher();
        List<Publisher> publishers = new ArrayList<>();

        publishers.add(publisher);

        when(publisherService.findAll()).thenReturn(publishers);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(API_PUBLISHERS)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void savePublisher() throws Exception {
        Publisher publisher = PublisherMother.createPublisher();

        when(publisherService.save(publisher)).thenReturn(publisher);

        mapper.writeValue(new File(TEST_PUBLISHER_JSON), publisher);
        String jsonPublisherToString = mapper.writeValueAsString(publisher);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(API_PUBLISHERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPublisherToString))
                .andExpect(status().isOk());
    }

    @Test
    void findPublisherById() throws Exception {
        Publisher publisher = PublisherMother.createPublisher();

        when(publisherService.findById(1L)).thenReturn(Optional.of(publisher));

        mapper.writeValue(new File(TEST_PUBLISHER_JSON), publisher);
        String jsonWriterToString = mapper.writeValueAsString(publisher);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(API_PUBLISHERS + "/1"))
                .andExpect(content().string(jsonWriterToString))
                .andExpect(status().isOk());
    }

    @WithMockUser(authorities = "ADMIN")
    @Test
    void deletePublisherById() throws Exception {
        mockMvc.perform(delete(API_PUBLISHERS + "/2"))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    void notDeleteAndGetForbidden() throws Exception {
        mockMvc.perform(delete(API_PUBLISHERS + "/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    void notDeleteAndGetUnauthorized() throws Exception {
        mockMvc.perform(delete(API_PUBLISHERS + "/1"))
                .andExpect(status().isUnauthorized());
    }
}