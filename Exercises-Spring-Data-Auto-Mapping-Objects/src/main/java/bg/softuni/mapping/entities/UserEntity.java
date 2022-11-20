package bg.softuni.mapping.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

import static bg.softuni.mapping.constants.Validations.EMAIL_PATTERN;

@Entity
@Table(name="users", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
@AllArgsConstructor
@Data
@Builder
@ToString
public class UserEntity extends BaseEntity {

    @Column(nullable = false)
    @Email(regexp = EMAIL_PATTERN)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name="full_name", nullable = false)
    private String fullName;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<GameEntity> games;

    @OneToMany(mappedBy = "user", targetEntity = OrderEntity.class, fetch = FetchType.EAGER)
    private Set<OrderEntity> orders;

    @Column
    private Boolean isAdmin;

    public UserEntity() {
        this.games = new HashSet<>();
        this.orders = new HashSet<>();
    }

    public UserEntity(String email, String password, String fullName) {
        this();
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

}
