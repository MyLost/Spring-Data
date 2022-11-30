package bg.softuni.xmlProccessing.domain.entities;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customers")
public class Customer extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime birthDate;

    @Column
    private Boolean isYoungDriver;

    @OneToMany(mappedBy = "customer", targetEntity = Sale.class, fetch = FetchType.EAGER)
    private Set<Sale> sales;
}