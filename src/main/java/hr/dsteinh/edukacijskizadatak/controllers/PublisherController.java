package hr.dsteinh.edukacijskizadatak.controllers;

import hr.dsteinh.edukacijskizadatak.model.legal_entity.Publisher;
import hr.dsteinh.edukacijskizadatak.service.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/publisher")
public class PublisherController {
    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }
    @GetMapping
    public List<Publisher> findAll() {
        return publisherService.findAll();
    }
    @PostMapping
    public Publisher save(@Validated @RequestBody Publisher publisher) {
        return publisherService.save(publisher);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Publisher> findById(@PathVariable(value = "id") long id) {
        Optional<Publisher> publisher = publisherService.findById(id);
        return publisher.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable(value = "id") long id) {
        if (publisherService.findById(id).isEmpty()) {
            return HttpStatus.NOT_FOUND;
        }
        publisherService.deleteById(id);
        return HttpStatus.OK;
    }
}
