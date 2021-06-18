package eu.senla.hotelBooking.api.services;

import eu.senla.hotelBooking.dto.GuestDto;
import eu.senla.hotelBooking.exceptions.NoRecordException;
import eu.senla.hotelBooking.model.Guest;

import java.util.List;

public interface GuestService{

    GuestDto create(Guest entity);

    List<GuestDto> getAll();

    GuestDto getById(Long id) throws NoRecordException;

}
