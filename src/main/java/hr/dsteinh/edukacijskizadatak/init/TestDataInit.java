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
        User mirko = new User("Mirko", "Kralj", "123124124");
        userService.save(mirko);
        User jovan = new User("Jovan", "Mirić", "9875311122");
        userService.save(jovan);
        User milica = new User("Milica", "Krmpotić", "523148979");
        userService.save(milica);

    }
}
