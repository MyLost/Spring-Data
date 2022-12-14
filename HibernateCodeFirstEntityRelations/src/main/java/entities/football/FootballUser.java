package entities.football;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="football_user")
public class FootballUser extends BaseEntity {
    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String fullName;

    @Column
    private BigDecimal balance;

}