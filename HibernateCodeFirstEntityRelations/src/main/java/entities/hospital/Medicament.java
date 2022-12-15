package entities.hospital;

import jakarta.persistence.*;


@Entity
@Table(name="medicaments")
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
}
