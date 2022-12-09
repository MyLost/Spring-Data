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
@Table(name = "albums")
public class Album extends BaseEntity {

    @Column
    private String name;

    @Column
    private String backgroundColor;

    @Column
    private Boolean isPublic;

    @ManyToMany(mappedBy = "albums")
    private Set<Picture> pictures;

    @ManyToOne(optional = false)
    private User owner;
}
