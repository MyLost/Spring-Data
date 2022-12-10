package bg.softuni.exercise.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "countries")
public class Country extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany()
    @JoinColumn(referencedColumnName = "id")
    private Set<Town> towns;
}
