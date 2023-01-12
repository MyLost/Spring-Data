package entityes;

import jakarta.persistence.*;

import java.math.BigDecimal;

//@Entity
//Create single table and all data from classes is in it!
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//Create table for each class and table are joined and we have not repeating of columns from other classes!
//@Inheritance(strategy = InheritanceType.JOINED)
//Create single table for each class with all information amd generation type strategy must be TABLE
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@DiscriminatorColumn(name="data_type")
//@Table(name="vehicle")
@MappedSuperclass
public abstract class Vehicle {

    @Id
    // Set auto increment field to true in table
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Create new sequence table for generation unique id
//    @GeneratedValue(strategy = GenerationType.TABLE)

//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Basic
    private String type;

    @Column
    private String model;

    @Column
    private BigDecimal price;

    @Column(name="fuel_type")
    private String fuelType;

    public Vehicle(String type) {
        this.type = type;
    }

    public Vehicle() {}

    public void setId(long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getFuelType() {
        return fuelType;
    }
}
