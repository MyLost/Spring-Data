package bg.softuni.mapping.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddGameDto extends EditGameDto {

    private LocalDate releaseDate;

    public AddGameDto(String title, String trailerId, String imageUrl, Float size, BigDecimal price, String description, LocalDate releaseDate) {
//        this.title = title;
//        this.trailerId = trailerId;
//        this.imageUrl = imageUrl;
//        this.size = size;
//        this.price = price;
//        this.description = description;
//        this.releaseDate = releaseDate;
        this.setTitle(title);
        this.setTrailerId(trailerId);
        this.setImageUrl(imageUrl);
        this.setSize(size);
        this.setPrice(price);
        this.setDescription(description);
        this.setReleaseDate(releaseDate);
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
