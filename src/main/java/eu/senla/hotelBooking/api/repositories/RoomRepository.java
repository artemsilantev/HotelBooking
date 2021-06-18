package eu.senla.hotelBooking.api.repositories;

import eu.senla.hotelBooking.model.Room;
import eu.senla.hotelBooking.model.enums.RoomStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Long> {

    List<Room> getRoomsByStatusEquals(RoomStatus status);

}
