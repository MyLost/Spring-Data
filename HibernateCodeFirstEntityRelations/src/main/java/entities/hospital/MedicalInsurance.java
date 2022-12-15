package entities.hospital;

import jakarta.persistence.*;

@Entity
@Table(name="medical_insurance")
public class MedicalInsurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;
}
