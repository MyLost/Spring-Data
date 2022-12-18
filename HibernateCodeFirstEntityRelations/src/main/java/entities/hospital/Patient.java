package entities.hospital;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private String address;

    private String email;

    private LocalDate dateOfBirth;

    private String picture;

    @OneToOne
    private MedicalInsurance medicalInsurance;

    @OneToMany
    private List<Visitation> visitation;

    @OneToOne
    private Diagnose diagnose;

    @OneToMany
    private List<Medicament> medicaments;
}
