package eu.senla.hotelBooking.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NoRecordException extends Exception {

    private String entityType;
    private Long id;

    @Override
    public String getMessage() {
        return "No Record by your criteria exists in Data Storage.";
    }
}
