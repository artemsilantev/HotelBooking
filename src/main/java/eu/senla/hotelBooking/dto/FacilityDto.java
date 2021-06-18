package eu.senla.hotelBooking.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FacilityDto {

    private String name;
    private Double price;
    private Date dateAdded;

}
