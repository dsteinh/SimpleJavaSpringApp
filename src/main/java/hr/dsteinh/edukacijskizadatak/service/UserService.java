package hr.dsteinh.edukacijskizadatak.service;

import hr.dsteinh.edukacijskizadatak.model.legal_entity.person.User;
import hr.dsteinh.edukacijskizadatak.repos.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User save(User person) {
        return userRepo.save(person);
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }
    public Optional<User> findByUsername(String username) {
        return userRepo.findUserByUsername(username);
    }

    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }

}
