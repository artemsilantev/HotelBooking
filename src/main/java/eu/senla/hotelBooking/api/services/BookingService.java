package eu.senla.hotelBooking.api.services;

import eu.senla.hotelBooking.dto.BookingDto;
import eu.senla.hotelBooking.dto.FacilityDto;
import eu.senla.hotelBooking.exceptions.NoRecordException;
import eu.senla.hotelBooking.model.Booking;
import eu.senla.hotelBooking.model.enums.SortKey;

import java.util.List;

public interface BookingService{

    BookingDto create(Booking booking);

    BookingDto getById(Long id) throws NoRecordException;

    List<BookingDto> getAll();

    List<FacilityDto> facilitySortedByKey(Long id, SortKey sortKey) throws NoRecordException;

    List<BookingDto> sortByKey(SortKey sortKey);
}
