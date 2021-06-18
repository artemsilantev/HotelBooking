package eu.senla.hotelBooking.dto;

import eu.senla.hotelBooking.model.enums.RoomStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDto {

    private Integer stars;
    private RoomStatus status;
    private Integer capacity;
    private Double price;

}
