package hr.dsteinh.edukacijskizadatak.controllers;

import hr.dsteinh.edukacijskizadatak.model.Rent;
import hr.dsteinh.edukacijskizadatak.service.RentService;
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

class RentControllerShould {

    @Mock
    RentService rentService;

    RentController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new RentController(rentService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void findAllRents() throws Exception {
        Rent rent = new Rent();
        rent.setId(1L);

        List<Rent> rents = new ArrayList<>();
        rents.add(rent);

        when(rentService.findAll()).thenReturn(rents);

        mockMvc.perform(get("/api/rent"))
                .andExpect(status().isOk());
    }

    @Test
    void saveRant() throws Exception {
        mockMvc.perform(post("/api/rent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "string",
                                  "price": 0,
                                  "quantity": 0,
                                  "description": "string",
                                  "isbn": "string",
                                  "writer": {
                                    "id": 4
                                 \s
                                  },
                                  "publisher": {
                                    "id": 7
                                \s
                                  }
                                }"""))
                .andExpect(status().isOk());
    }

    @Test
    void findRentById() throws Exception {
        Rent rent = new Rent();
        rent.setId(1L);

        when(rentService.findById(1L)).thenReturn(Optional.of(rent));

        mockMvc.perform(get("/api/rent/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteRentById() throws Exception {
        mockMvc.perform(delete("/api/rent/2"))
                .andExpect(status().isOk());
    }
}