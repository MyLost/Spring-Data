package bg.softuni.mapping.adapters;

import bg.softuni.mapping.dtos.AddGameDto;
import bg.softuni.mapping.dtos.EditGameDto;
import bg.softuni.mapping.dtos.GameDto;
import bg.softuni.mapping.entities.GameEntity;
import org.springframework.stereotype.Service;

@Service
public class GameAdapter {

    public GameEntity fromDtoAddGameDto (AddGameDto dto) {

        GameEntity gameEntity = new GameEntity();
        gameEntity.setDescription(dto.getDescription());
        gameEntity.setSize(dto.getSize());
        gameEntity.setTitle(dto.getTitle());
        gameEntity.setPrice(dto.getPrice());
        gameEntity.setImageUrl(dto.getImageUrl());
        gameEntity.setTrailerId(dto.getTrailerId());
        gameEntity.setReleaseDate(dto.getReleaseDate());

        return gameEntity;
    }

    public GameEntity mergeGame(GameEntity gameEntity, EditGameDto dto) {

        if(dto.getDescription() != null) gameEntity.setDescription(dto.getDescription());
        if(dto.getSize() != null) gameEntity.setSize(dto.getSize());
        if(dto.getTitle() != null) gameEntity.setTitle(dto.getTitle());
        if(dto.getImageUrl() != null) gameEntity.setImageUrl(dto.getImageUrl());
        if(dto.getTrailerId() != null) gameEntity.setTrailerId(dto.getTrailerId());
        if(dto.getPrice() != null) gameEntity.setPrice(dto.getPrice());

        return gameEntity;
    }

    public GameDto toGameDto(GameEntity gameEntity) {
        return GameDto.builder()
                .id(gameEntity.getId())
                .title(gameEntity.getTitle())
                .size(gameEntity.getSize())
                .price(gameEntity.getPrice())
                .description(gameEntity.getDescription())
                .releaseDate(gameEntity.getReleaseDate())
                .imageUrl(gameEntity.getImageUrl())
                .trailerId(gameEntity.getTrailerId())
                .build();
    }
}
