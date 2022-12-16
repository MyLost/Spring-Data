package bg.softuni.mapping.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity extends BaseEntity {

    @ManyToOne
    private UserEntity user;

    @ManyToMany
    private Set<GameEntity> games;

    public OrderEntity() {
        this.games = new HashSet<>();
    }

    public OrderEntity(UserEntity user, Set<GameEntity> games) {
        this.user = user;
        this.games = games;
    }
}
