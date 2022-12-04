package bg.softuni.mapping.service.game;

import bg.softuni.mapping.dtos.AddGameDto;
import bg.softuni.mapping.dtos.DeleteGameDto;
import bg.softuni.mapping.dtos.EditGameDto;
import bg.softuni.mapping.dtos.GameDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GameService {

    void addGame(AddGameDto dto);

    void editGame(EditGameDto dto);

    void deleteGame(DeleteGameDto dto);

    List<GameDto> getAllGames();

    GameDto detailsGame(String title);

    List<String> ownedGames();
}
