package entities;

import orm.Column;
import orm.Entity;
import orm.Id;

import java.time.LocalDate;

@Entity(name="students")
public class Student {

    public Student() {
    }

    @Id
    @Column(name="id")
    private long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="age")
    private int age;

    @Column(name="registration")
    private LocalDate registration;


    public Student(String firstName, String lastName, int age, LocalDate registration) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.registration = registration;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRegistration(LocalDate registration) {
        this.registration = registration;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getRegistration() {
        return registration;
    }
}
