package hr.dsteinh.edukacijskizadatak.service;

import hr.dsteinh.edukacijskizadatak.model.Rent;
import hr.dsteinh.edukacijskizadatak.mother.RentMother;
import hr.dsteinh.edukacijskizadatak.repos.RentRepo;
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
class RentServiceShould {

    @Autowired
    private RentService rentService;
    @MockBean
    private RentRepo rentRepo;

    @Test
    void findAllRents() {
        List<Rent> expectedRents = new ArrayList<>();
        expectedRents.add(new Rent());

        given(rentRepo.findAll()).willReturn(expectedRents);

        // When
        List<Rent> actualRents = rentService.findAll();

        // Then
        assertEquals(expectedRents, actualRents);
    }

    @Test
    void saveRent() {
        Rent expectedRent = RentMother.createRent();

        given(rentRepo.save(expectedRent)).willReturn(expectedRent);

        // When
        Rent actualRent = rentService.save(expectedRent);

        // Then
        assertEquals(expectedRent, actualRent);
    }

    @Test
    void findRentById() {
        long id = 1L;
        Rent expectedRent = RentMother.createRent();

        given(rentRepo.findById(id)).willReturn(Optional.of(expectedRent));

        // When
        Rent actualRent = rentService.findById(id).get();

        // Then
        assertEquals(expectedRent, actualRent);
    }

    @Test
    void deleteRentById() {
        long id = 1L;

        // When
        rentService.deleteById(id);

        // Then
        verify(rentRepo, times(1)).deleteById(id);
    }
}