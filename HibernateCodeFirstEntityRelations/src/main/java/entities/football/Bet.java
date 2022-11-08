package entities.football;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table
public class Bet extends BaseEntity {

    @Column
    private BigDecimal betMoney;

    @Column
    private Date timeOfBet;

    @ManyToOne
    private FootballUser user;
}