package bg.softuni.exercise.config;

import bg.softuni.exercise.ConsoleApplication;
import bg.softuni.exercise.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppicationConfig {

    private UserService userService;

    public AppicationConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public ConsoleApplication provideApplication() {
        return new ConsoleApplication(this.userService);
    }
}
