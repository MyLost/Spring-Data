package entities.hospital;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="diagnose")
public class Diagnose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "namePayment System")
    private String name;
}
