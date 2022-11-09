package bg.softuni.modelMapperAplication.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column
    private BigDecimal salary;

    @Column(name="birth_day")
    private LocalDate birthDay;

    @OneToOne
    private Address address;

    public Employee(String firstName, BigDecimal salary, Address address) {
        this.salary = salary;
        this.firstName = firstName;
        this.address = address;
    }
}
