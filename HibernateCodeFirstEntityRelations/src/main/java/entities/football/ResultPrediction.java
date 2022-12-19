package entities.football;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table
public class ResultPrediction extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ResultPredictionValue resultPrediction;
}