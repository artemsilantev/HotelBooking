package eu.senla.hotelBooking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Guest extends BaseEntity {

    private String firstName;
    private String secondName;
    private String phone;
    @ManyToMany
    private List<Booking> booking = new ArrayList<>();

    public Guest(String firstName, String secondName, String phone) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Guest ID: " + getId() +
                "\n\tFirst name: " + firstName +
                "\n\tSecond name: " + secondName +
                "\n\tPhone: " + phone + "\n";
    }

}
