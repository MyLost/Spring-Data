package softuni.exam.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cities")
public class City extends BaseEntity {

    @Min(value = 2)
    @Max(value = 60)
    @Column(unique = true, nullable = false)
    private String cityName;

    private String description;

    private Long population;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;


}
