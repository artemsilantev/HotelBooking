package eu.senla.hotelBooking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Facility extends BaseEntity {

    private String name;
    private Double price;
    private Date dateAdded;
    @ManyToMany
    private List<Booking> bookings = new ArrayList<>();

    public Facility(String name, Double price) {
        this.name = name;
        this.price = price;
        dateAdded = new Date();
    }

    @Override
    public String toString() {
        return "Facility ID: " + getId() +
                "\n\tName: " + name +
                "\n\tPrice: " + price +
                "\n\tDate: " + dateAdded + "\n";
    }
}