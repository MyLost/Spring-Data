package bg.softuni.exercise;

import bg.softuni.exercise.entities.User;
import bg.softuni.exercise.services.UserService;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ConsoleApplication {

    private final UserService userService;

    @Autowired
    public ConsoleApplication(final UserService userService) {
        this.userService = userService;
    }

    public void startApplication() {
        if (this.userService.getUsersCount() == 0L) {
            addUsers();
        }
        printAllUsersByEmailProvider("abv.bg");
        deactivateUsersInactiveSinceDate(new Date());
        printAllUsersByAgeRange();
    }

    private void printAllUsersByAgeRange() {
        this.userService.getUserNamesAndAgeByAgeRange(20, 25)
                .forEach(System.out::println);
    }

    private void deactivateUsersInactiveSinceDate(final Date date) {
        this.userService.deactivateInactiveUsers(date);
    }

    private void printAllUsersByEmailProvider(final String provider) {
        this.userService.getAllUsersByEmailProvider(provider)
                .forEach(user -> System.out.println(user.getUserName() + " " + user.getEmail()));
    }

    private void addUsers() {
        for (int i = 1; i <= 1000; i++) {
            this.userService.save(User.builder()
                    .userName("Mars" + i)
                    .password("maRsmarsd%" + i)
                    .email("mail" + i + "mars@webmaster.org")
                    .age(i % 120 + 1)
                    .firstName("Phobos" + i)
                    .lastName("Deimos" + i)
                    .registeredOn(new Date())
                    .lastTimeLoggedIn(new Date())
                    .isDeleted(false)
                    .build());
        }
    }


}



