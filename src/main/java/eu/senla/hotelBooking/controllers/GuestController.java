package eu.senla.hotelBooking.controllers;

import eu.senla.hotelBooking.api.services.GuestService;
import eu.senla.hotelBooking.dto.GuestDto;
import eu.senla.hotelBooking.exceptions.NoRecordException;
import eu.senla.hotelBooking.model.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/guests")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @GetMapping("/{id}")
    public ResponseEntity<GuestDto> getById(@PathVariable Long id) throws NoRecordException {
        return new ResponseEntity<>(guestService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GuestDto> createGuest(@RequestBody Guest guest) {
        return new ResponseEntity<>(guestService.create(guest), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GuestDto>> getAllGuests() {
        return new ResponseEntity<>(guestService.getAll(), HttpStatus.OK);
    }

}
