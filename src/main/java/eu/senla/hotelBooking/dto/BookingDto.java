package eu.senla.hotelBooking.dto;

import eu.senla.hotelBooking.model.Room;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class BookingDto {

    private Date registrationDate;
    private Date checkOutDate;
    private Room room;
    private List<GuestDto> guests;
    private List<FacilityDto> facilities;

}
