package bg.softuni.mapping.repository;

import bg.softuni.mapping.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

    Optional<GameEntity> findByTitle(String title);
}
