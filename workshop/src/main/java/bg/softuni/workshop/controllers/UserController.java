package bg.softuni.workshop.controllers;


import bg.softuni.workshop.models.dtos.LoginDto;
import bg.softuni.workshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("/users/login")
    public String doLogin(LoginDto loginDto) {
        try {
            userService.login(loginDto);
        } catch (Exception e) {
            return "user/login";
        }
        return "redirect:/home";
    }
}
