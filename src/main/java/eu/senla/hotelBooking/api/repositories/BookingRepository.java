package eu.senla.hotelBooking.api.repositories;

import eu.senla.hotelBooking.model.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long> {
}
