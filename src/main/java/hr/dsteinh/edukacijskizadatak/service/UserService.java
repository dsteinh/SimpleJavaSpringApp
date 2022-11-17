package hr.dsteinh.edukacijskizadatak.service;

import hr.dsteinh.edukacijskizadatak.model.User;
import hr.dsteinh.edukacijskizadatak.repos.UserRepo;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Iterable<User> findAll(){
        return userRepo.findAll();
    }
    public User save(User user){
        return userRepo.save(user);
    }
}
