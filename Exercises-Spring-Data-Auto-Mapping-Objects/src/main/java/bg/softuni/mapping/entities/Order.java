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
public class Order extends BaseEntity {

    @ManyToOne
    private User user;

    @ManyToMany
    private Set<Game> games;

    public Order() {
        this.games = new HashSet<>();
    }

    public Order(User user, Set<Game> games) {
        this.user = user;
        this.games = games;
    }
}
