package hr.dsteinh.edukacijskizadatak.controllers;

import hr.dsteinh.edukacijskizadatak.model.Rent;
import hr.dsteinh.edukacijskizadatak.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/rents")
@RequiredArgsConstructor
public class RentController {
    private final RentService rentService;

    @GetMapping
    public List<Rent> findAll() {
        return rentService.findAll();
    }

    @PostMapping
    public Rent save(@Validated @RequestBody Rent rent) {
        return rentService.save(rent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rent> findById(@PathVariable(value = "id") long id) {
        Optional<Rent> rent = rentService.findById(id);
        return rent.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable(value = "id") long id) {
        if (rentService.findById(id).isEmpty()) {
            return HttpStatus.NOT_FOUND;
        }
        rentService.deleteById(id);
        return HttpStatus.OK;
    }
}
