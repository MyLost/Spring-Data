package entities.football;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table
public class Competition extends BaseEntity {

//    – Id, Name, Type (local, national, international)

    @Column
    private String name;

    @ManyToOne
    private CompetitionType type;
}