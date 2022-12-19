package bg.softuni.xmlProccessing.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {
    @OneToOne(targetEntity = Car.class)
    private Car car;
    @ManyToOne(targetEntity = Customer.class)
    private Customer customer;

    private Double discount;
}
