package eu.senla.hotelBooking.api.repositories;

import eu.senla.hotelBooking.model.Guest;
import org.springframework.data.repository.CrudRepository;

public interface GuestRepository extends CrudRepository<Guest, Long> {
}
