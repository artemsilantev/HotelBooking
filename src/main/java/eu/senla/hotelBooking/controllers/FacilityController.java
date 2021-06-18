package eu.senla.hotelBooking.controllers;

import eu.senla.hotelBooking.api.services.FacilityService;
import eu.senla.hotelBooking.controllers.response.ResponseBody;
import eu.senla.hotelBooking.dto.FacilityDto;
import eu.senla.hotelBooking.exceptions.NoRecordException;
import eu.senla.hotelBooking.model.Facility;
import eu.senla.hotelBooking.model.enums.SortKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/facilities")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @GetMapping
    public ResponseEntity<List<FacilityDto>> getAllFacilities() {
        return new ResponseEntity<>(facilityService.getAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> changeFacilityInfo(@RequestBody Facility facility) {
        facilityService.changeInfo(facility);
        return ResponseBody.successOperation();
    }

    @GetMapping("/sort")
    public ResponseEntity<List<FacilityDto>> getFacilitiesSortedByKey(@RequestParam SortKey key) {
        return new ResponseEntity<>(facilityService.sortByKey(key), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FacilityDto> createFacility(@RequestBody Facility facility) {
        return new ResponseEntity<>(facilityService.create(facility), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacilityDto> getFacilityById(@PathVariable Long id) throws NoRecordException {
        return new ResponseEntity<>(facilityService.getById(id), HttpStatus.OK);
    }

}
