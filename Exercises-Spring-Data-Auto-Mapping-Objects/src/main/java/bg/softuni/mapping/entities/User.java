package bg.softuni.mapping.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

import static bg.softuni.mapping.constants.Validations.EMAIL_PATTERN;

@Entity
@Table(name="users")
@AllArgsConstructor
@Data
public class User extends BaseEntity {

    @Column(nullable = false)
    @Email(regexp = EMAIL_PATTERN)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name="full_name", nullable = false)
    private String fullName;

    @ManyToMany
    private Set<Game> games;

    @OneToMany(mappedBy = "user", targetEntity = Order.class, fetch = FetchType.EAGER)
    private Set<Order> orders;

    @Column
    private Boolean isAdmin;

    public User() {
        this.games = new HashSet<>();
        this.orders = new HashSet<>();
    }

    public User(String email, String password, String fullName) {
        this();
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

}
