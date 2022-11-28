package hr.dsteinh.edukacijskizadatak.controllers;

import hr.dsteinh.edukacijskizadatak.model.legal_entity.person.User;
import hr.dsteinh.edukacijskizadatak.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserEndpointController {

    private final UserService userService;

    public UserEndpointController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable(value = "id") long id) {
        Optional<User> user = userService.findById(id);
        return user.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public User createOrUpdateUser(@Validated @RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteById(@PathVariable(value = "id") long id) {
        if (userService.findById(id).isEmpty()) {
            return HttpStatus.NOT_FOUND;
        }
        userService.deleteById(id);
        return HttpStatus.OK;
    }

}

