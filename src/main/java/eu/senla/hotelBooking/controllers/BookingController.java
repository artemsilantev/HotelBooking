package eu.senla.hotelBooking.controllers;

import eu.senla.hotelBooking.api.services.BookingService;
import eu.senla.hotelBooking.api.services.FacilityService;
import eu.senla.hotelBooking.api.services.GuestService;
import eu.senla.hotelBooking.dto.BookingDto;
import eu.senla.hotelBooking.dto.FacilityDto;
import eu.senla.hotelBooking.exceptions.NoRecordException;
import eu.senla.hotelBooking.model.Booking;
import eu.senla.hotelBooking.model.enums.SortKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private FacilityService facilityService;
    @Autowired
    private GuestService guestService;

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody Booking booking) {
        booking.getFacilities().forEach(facility -> facilityService.create(facility));
        booking.getGuests().forEach(guest -> guestService.create(guest));
        return new ResponseEntity<>(bookingService.create(booking), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BookingDto>> getAllBookings() {
        return new ResponseEntity<>(bookingService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable Long id) throws NoRecordException {
        return new ResponseEntity<>(bookingService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/sort")
    public ResponseEntity<List<BookingDto>> getBookingsSortedByKey(@RequestParam SortKey key) {
        return new ResponseEntity<>(bookingService.sortByKey(key), HttpStatus.OK);
    }

    @GetMapping("/{id}/facilities/sort")
    public ResponseEntity<List<FacilityDto>> getFacilitySortedByKey(@PathVariable Long id,
                                                                    @RequestParam SortKey key) throws NoRecordException {
        return new ResponseEntity<>(bookingService.facilitySortedByKey(id, key), HttpStatus.OK);
    }

}