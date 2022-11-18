package bg.softuni.mapping.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class GameDto {

    private Long id;

    private String title;

    private String trailerId;

    private String imageUrl;

    private Float size;

    private BigDecimal price;

    private String description;

    private LocalDate releaseDate;
}
