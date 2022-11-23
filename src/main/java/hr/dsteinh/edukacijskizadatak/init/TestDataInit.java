package hr.dsteinh.edukacijskizadatak.init;

import hr.dsteinh.edukacijskizadatak.model.User;
import hr.dsteinh.edukacijskizadatak.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestDataInit implements CommandLineRunner {
    private final UserService userService;

    public TestDataInit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        User tony = new User("Tony", "Stark", "3000");
        userService.save(tony);
        User steve = new User("Steve", "Rogers", "9875311122");
        userService.save(steve);
        User milica = new User("Milica", "Krmpotić", "523148979");
        userService.save(milica);

    }
}
