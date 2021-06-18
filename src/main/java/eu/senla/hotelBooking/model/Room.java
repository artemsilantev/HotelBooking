package eu.senla.hotelBooking.model;

import eu.senla.hotelBooking.model.enums.RoomStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Room extends BaseEntity {

    private Integer stars;
    private RoomStatus status;
    private Integer capacity;
    private Double price;
    @OneToMany
    private List<Booking> bookings = new ArrayList<>();

    public Room(Double price, Integer capacity, Integer stars) {
        status = RoomStatus.AVAILABLE;
        this.price = price;
        this.capacity = capacity;
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Room ID: " + getId() +
                "\n\tStatus: " + status +
                "\n\tPrice: " + price +
                "\n\tCapacity: " + capacity +
                "\n\tStars: " + stars + "\n";
    }

}
