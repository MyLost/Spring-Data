package entities.sales;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="sales")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "storeLocation", referencedColumnName = "id")
    private StoreLocation storeLocation;

    @Column(name="date")
    Date date;
}
