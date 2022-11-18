package bg.softuni.mapping.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class EditGameDto {

    protected Long id;
    protected String title;

    protected String trailerId;

    protected String imageUrl;

    protected Float size;

    protected BigDecimal price;

    protected String description;



    public EditGameDto(String title, String trailerId, String imageUrl, Float size, BigDecimal price, String description) {
//        this.title = title;
//        this.trailerId = trailerId;
//        this.imageUrl = imageUrl;
//        this.size = size;
//        this.price = price;
//        this.description = description;
//        validate();

        setTitle(title);
        setTrailerId(trailerId);
        setImageUrl(imageUrl);
        setSize(size);
        setPrice(price);
        setDescription(description);
    }

    public void setTitle(String title) {

        if(!Character.isUpperCase(title.charAt(0)) && (title.length() > 3 || title.length() < 100)) {
            throw new RuntimeException("Title don't start with uppercase letter ot title don't in limits from 3 to 100");
        }

        this.title = title;
    }

    public void setTrailerId(String trailerId) {

        if(trailerId.length() == 10) {
            throw new RuntimeException("Trailer id sis not valid!!!");
        }

        this.trailerId = trailerId;
    }

    public void setImageUrl(String imageUrl) {

        if(!(imageUrl.startsWith("http://") || imageUrl.startsWith("https://"))) {
            throw new RuntimeException("Wrong protocol for image!");
        }

        this.imageUrl = imageUrl;
    }

    public void setSize(Float size) {

        if(size < 0) {
            throw new RuntimeException("Size can't be negative!");
        }

        this.size = size;
    }

    public void setPrice(BigDecimal price) {

        if(price.compareTo(BigDecimal.ZERO) < 0 ) {
            throw new RuntimeException("Price can't be negative!");
        }

        this.price = price;
    }

    public void setDescription(String description) {

        if(description.length() < 20) {
            throw new RuntimeException("Description must be at least 20 symbols");
        }

        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    protected void validate() {
//
//        if(!Character.isUpperCase(title.charAt(0)) && (title.length() > 3 || title.length() < 100)) {
//            throw new RuntimeException("Title don't start with uppercase letter ot title don't in limits from 3 to 100");
//        }
//
//        if(price.compareTo(BigDecimal.ZERO) < 0 ) {
//            throw new RuntimeException("Price can't be negative!");
//        }
//
//        if(size < 0) {
//            throw new RuntimeException("Size can't be negative!");
//        }
//
//        if(trailerId.length() == 10) {
//            throw new RuntimeException("Trailer id sis not valid!!!");
//        }
//
//        if(!(imageUrl.startsWith("http://") || imageUrl.startsWith("https://"))) {
//            throw new RuntimeException("Wrong protocol for image!");
//        }
//
//        if(description.length() < 20) {
//            throw new RuntimeException("Description must be at least 20 symbols");
//        }
//    }
}
