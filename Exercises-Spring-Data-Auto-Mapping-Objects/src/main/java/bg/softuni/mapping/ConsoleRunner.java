package bg.softuni.mapping;

import bg.softuni.mapping.dtos.*;
import bg.softuni.mapping.providers.CurrentUserProvider;
import bg.softuni.mapping.service.game.GameService;
import bg.softuni.mapping.service.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import static bg.softuni.mapping.constants.Commands.*;

/*
    This is simple implementation of soft uni task Spring Data Auto Mapping Objects. Last task with asterisk are not implemented!
    Project can be improved a lot, but lays foundations on which to build!
    *
    *
    *
 */
@Component
public final class ConsoleRunner implements CommandLineRunner {

    private static final Scanner scanner = new Scanner(System.in);

    private final UserService userService;

    private final GameService gameService;

    public ConsoleRunner(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("System expect you command!");

        String[] input = readInput();

        while(!"END".equals(input[0])) {


            switch (input[0]) {

                case REGISTER_USER -> register(input);
                case LOGIN -> login(input);
                case LOGOUT -> logout();
                case ADD_GAME -> addGame(input);
                case EDIT_GAME -> editGame(input);
                case DELETE_GAME -> deleteGame(input);
                case ALL_GAMES -> allGames();
                case DETAIL_GAME -> detailsGame(input);
                case OWNED_GAMES -> ownedGames();

            }

            System.out.println("System expect you command!");
            input = readInput();
        }
    }

    private void ownedGames() {

        List<String> ownedGameTitles = this.gameService.ownedGames();

        ownedGameTitles.forEach(System.out::println);
    }

    private void detailsGame(String[] input) {

        GameDto gameDto = this.gameService.detailsGame(input[1]);

        System.out.println("Title: " + gameDto.getTitle());
        System.out.println("Price: " + gameDto.getPrice());
        System.out.println("Description: " + gameDto.getDescription());
        System.out.println("Release date: " +gameDto.getReleaseDate());

    }

    private void allGames() {

        List<GameDto> games = this.gameService.getAllGames();

        games.stream().forEach(game -> System.out.println(String.format("%s %.2f", game.getTitle(), game.getPrice())));
    }

    private void deleteGame(String[] input) {

        DeleteGameDto deleteGameDto = new DeleteGameDto();
        deleteGameDto.setId(Long.parseLong(input[1]));

        this.gameService.deleteGame(deleteGameDto);
    }

    private void editGame(String[] input) {

        EditGameDto editGameDto = new EditGameDto();
        editGameDto.setId(Long.parseLong(input[1]));
        editGameDto.setPrice(new BigDecimal(input[2].split("=")[1]));
        editGameDto.setSize(Float.valueOf(input[3].split("=")[1]));

        this.gameService.editGame(editGameDto);

    }

    private void addGame(String[] input) {

        AddGameDto addGameDto = new AddGameDto(input[1], input[4], input[5], Float.valueOf(input[3]), new BigDecimal(input[2]), input[6], LocalDate.parse(input[7], DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        this.gameService.addGame(addGameDto);

    }

    private String[] readInput() {
        return scanner.nextLine().split("\\|");
    }

    private void register(String[] input) {

         UserRegisterDto userRegisterDto = new UserRegisterDto(input[1], input[2], input[3] , input[4]);
         userService.registerUser(userRegisterDto);
    }

    private void login(String[] input) {
        UserLoginDto userLoginDto = new UserLoginDto(input[1], input[2]);
        userService.loginUser(userLoginDto);
    }

    private void logout() {

        if(CurrentUserProvider.getCurrentUser() == null) {
            throw new RuntimeException("Cannot log out. No user was logged in.");
//            System.out.println("Before you log out need to log in");
//            String[] input = readInput();
//            login(input);
        }

       userService.logoutUser();
    }
}
