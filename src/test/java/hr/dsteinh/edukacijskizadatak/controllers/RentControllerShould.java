package hr.dsteinh.edukacijskizadatak.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hr.dsteinh.edukacijskizadatak.model.Rent;
import hr.dsteinh.edukacijskizadatak.mother.RentMother;
import hr.dsteinh.edukacijskizadatak.service.RentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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

@WebMvcTest(RentController.class)
class RentControllerShould {

    public static final String TEST_RENT_JSON = "src/test/resources/controller/test_rent.json";
    public static final String API_RENTS = "/api/rents";
    @Autowired
    MockMvc mockMvc;
    @MockBean
    RentService rentService;
    private final ObjectMapper mapper = new JsonMapper();


    @Test
    void findAllRents() throws Exception {
        Rent rent = RentMother.createRent();
        List<Rent> rents = new ArrayList<>();

        rents.add(rent);

        when(rentService.findAll()).thenReturn(rents);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(API_RENTS)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveRant() throws Exception {
        mapper.registerModule(new JavaTimeModule());
        Rent rent = mapper.readValue(new File(TEST_RENT_JSON), Rent.class);

        when(rentService.save(rent)).thenReturn(rent);

        String string = mapper.writeValueAsString(rent);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(API_RENTS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(string))
                .andExpect(status().isOk());
    }

    @Test
    void findRentById() throws Exception {
        mapper.registerModule(new JavaTimeModule());
        Rent rent = mapper.readValue(new File(TEST_RENT_JSON), Rent.class);

        when(rentService.findById(1L)).thenReturn(Optional.of(rent));

        String jsonRentToString = mapper.writeValueAsString(rent);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(API_RENTS + "/1"))
                .andExpect(content().string(jsonRentToString))
                .andExpect(status().isOk());
    }

    @Test
    void deleteRentById() throws Exception {
        mockMvc.perform(delete(API_RENTS + "/2"))
                .andExpect(status().isOk());
    }
}