package bg.softuni.json.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name="products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product extends BaseEntity {

    @Column(nullable = false)
    @Size(min = 3)
    private String name;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    @Fetch(FetchMode.JOIN)
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    @Fetch(FetchMode.JOIN)
    private User seller;

    @Fetch(FetchMode.JOIN)
    @ManyToMany
    private Set<Category> categories;
}
