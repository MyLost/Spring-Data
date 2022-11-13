package bg.softuni.mapping;

import bg.softuni.mapping.dtos.UserRegister;
import bg.softuni.mapping.service.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Scanner;

import static bg.softuni.mapping.constants.Commands.REGISTER_USER;
import static bg.softuni.mapping.constants.Validations.COMMAND_NOT_FOUND;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private static final Scanner scanner = new Scanner(System.in);

    private final UserService userService;

    public ConsoleRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        final String[] input = scanner.nextLine().split("\\|");
        final String command = input[0];

        switch (command) {
            case REGISTER_USER -> userService.registerUser(new UserRegister(input[1], input[2], input[3] , input[4]));
            default -> throw new IllegalArgumentException(COMMAND_NOT_FOUND);
        }



    }
}
