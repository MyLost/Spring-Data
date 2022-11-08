package entities.paymentSystem;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BillingDetail {

    @Id
    @Column(unique = true, length = 30)
    private String number;

    @ManyToOne
    private User owner;
}
