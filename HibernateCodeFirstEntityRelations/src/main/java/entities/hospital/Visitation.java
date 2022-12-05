package entities.hospital;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="visitation")
public class Visitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="visitation_date")
    private LocalDate visitationDate;

    @Column(name="comment")
    private String comment;
}
