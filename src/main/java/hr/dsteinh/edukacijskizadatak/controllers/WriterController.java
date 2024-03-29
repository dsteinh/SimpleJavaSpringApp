package hr.dsteinh.edukacijskizadatak.controllers;

import hr.dsteinh.edukacijskizadatak.model.Writer;
import hr.dsteinh.edukacijskizadatak.service.WriterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/writers")
@RequiredArgsConstructor
public class WriterController {
    private final WriterService writerService;

    @GetMapping
    public List<Writer> findAll() {
        return writerService.findAll();
    }

    @PostMapping
    public Writer save(@Validated @RequestBody Writer writer) {
        return writerService.save(writer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Writer> findById(@PathVariable(value = "id") long id) {
        Optional<Writer> writer = writerService.findById(id);
        return writer.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable(value = "id") long id) {
        if (writerService.findById(id).isEmpty()) {
            return HttpStatus.NOT_FOUND;
        }
        writerService.deleteById(id);
        return HttpStatus.OK;
    }
}
