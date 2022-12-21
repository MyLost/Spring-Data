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
@Table(name = "towns")
public class Town extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Country country;

    @OneToMany(mappedBy = "bornTown")
    private Set<User> born;

    @OneToMany(mappedBy = "currentlyLiving")
    private Set<User> live;

}
