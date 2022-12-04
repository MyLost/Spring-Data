package bg.softuni.mapping.service.game;

import bg.softuni.mapping.adapters.GameAdapter;
import bg.softuni.mapping.dtos.AddGameDto;
import bg.softuni.mapping.dtos.DeleteGameDto;
import bg.softuni.mapping.dtos.EditGameDto;
import bg.softuni.mapping.dtos.GameDto;
import bg.softuni.mapping.entities.GameEntity;
import bg.softuni.mapping.entities.UserEntity;
import bg.softuni.mapping.providers.CurrentUserProvider;
import bg.softuni.mapping.repository.GameRepository;
import bg.softuni.mapping.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    private GameAdapter gameAdapter;

    private UserRepository userRepository;

    public GameServiceImpl(GameRepository gameRepository, GameAdapter gameAdapter, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.gameAdapter = gameAdapter;
        this.userRepository = userRepository;
    }

    @Override
    public void addGame(AddGameDto dto) {

        GameEntity gameEntity = gameAdapter.fromDtoAddGameDto(dto);
        this.gameRepository.save(gameEntity);

        System.out.println(String.format("Added %s", gameEntity.getTitle()));
    }

    @Override
    public void editGame(EditGameDto dto) {

        GameEntity gameEntity = this.gameRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Game not Found!!!"));
        this.gameRepository.save(gameEntity);

        System.out.println(String.format("Edited %s", gameEntity.getTitle()));
    }

    @Override
    public void deleteGame(DeleteGameDto dto) {

        GameEntity gameEntity = this.gameRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Game not Found!!!"));
        this.gameRepository.delete(gameEntity);

        System.out.println(String.format("Deleted %s", gameEntity.getTitle()));
    }

    @Override
    public List<GameDto> getAllGames() {

        List<GameEntity> gameEntities = this.gameRepository.findAll();

        return gameEntities.stream().map(game -> this.gameAdapter.toGameDto(game)).toList();
    }

    @Override
    public GameDto detailsGame(String title) {

        GameEntity gameEntity = this.gameRepository.findByTitle(title).orElseThrow(() -> new RuntimeException("Game not Found!!!"));

        return this.gameAdapter.toGameDto(gameEntity);
    }

    @Override
    public List<String> ownedGames() {

        if(!CurrentUserProvider.isUserLoggedIn()) {
            throw new RuntimeException("There no logged user! To Perform this task you must logged in!!!");
        }

        UserEntity userEntity = this.userRepository.findById(CurrentUserProvider.getCurrentUser().getId()).orElseThrow(() -> new RuntimeException("Game not Found!!!"));

        return userEntity.getGames().stream().map(game -> game.getTitle()).toList();
    }
}
