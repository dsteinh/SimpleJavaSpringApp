package hr.dsteinh.edukacijskizadatak.controllers;

import hr.dsteinh.edukacijskizadatak.model.User;
import hr.dsteinh.edukacijskizadatak.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"", "/", "/user", "/user/index"})
    public String showUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/index";
    }

    @GetMapping("/user/create_user")
    public String showCreateUserForm() {
        return "user/create_user";
    }

    @PostMapping("/user/create_user")
    public RedirectView saveNewUser(@ModelAttribute User newUser) {
        System.out.println(newUser);
        userService.save(newUser);
        return new RedirectView("index");
    }
}
