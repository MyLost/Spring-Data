package bg.softuni.mapping.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="games")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Game extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(name="trailer_id")
    private String trailerId;

    @Column(name="image_url")
    private String imageUrl;

    @Column
    private Float size;

    @Column
    private BigDecimal price;

    @Column
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

}
