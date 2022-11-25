package hr.dsteinh.edukacijskizadatak.service;

import hr.dsteinh.edukacijskizadatak.model.User;
import hr.dsteinh.edukacijskizadatak.repos.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    public User createOrUpdateUser(User user) {
        return userRepo.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }
    public boolean existsById(Long id){
        return userRepo.existsById(id);
    }
}
