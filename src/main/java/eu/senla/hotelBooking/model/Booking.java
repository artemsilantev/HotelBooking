package eu.senla.hotelBooking.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseEntity {

    private Date registrationDate;
    private Date checkOutDate;
    @ManyToOne
    private Room room;
    @ManyToMany
    private List<Guest> guests = new ArrayList<>();
    @ManyToMany
    private List<Facility> facilities = new ArrayList<>();

    private String guestsToString() {
        StringBuilder result = new StringBuilder();
        for (Guest guest : guests) {
            result.append(guest.toString());
        }
        return result.toString();
    }

    private String facilitiesToString() {
        StringBuilder result = new StringBuilder();
        for (Facility facility : facilities) {
            result.append(facility.toString());
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return "Booking ID: " + getId() +
                "\n\tRegistration date: " + registrationDate +
                "\n\tCheckout date: " + checkOutDate +
                room + guestsToString() + facilitiesToString() + "\n";
    }

}







