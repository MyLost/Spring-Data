package entityes;

import jakarta.persistence.Basic;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PassengerVehicle extends Vehicle {

    @Basic
    private int seats;

    public PassengerVehicle(String carType) {
        super(carType);
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
