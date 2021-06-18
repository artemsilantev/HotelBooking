package eu.senla.hotelBooking.controllers;

import eu.senla.hotelBooking.api.services.RoomService;
import eu.senla.hotelBooking.controllers.response.ResponseBody;
import eu.senla.hotelBooking.dto.RoomDto;
import eu.senla.hotelBooking.exceptions.NoRecordException;
import eu.senla.hotelBooking.model.Room;
import eu.senla.hotelBooking.model.enums.FilterKey;
import eu.senla.hotelBooking.model.enums.RoomStatus;
import eu.senla.hotelBooking.model.enums.SortKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/{id}")
    public ResponseEntity<RoomDto> getRoom(@PathVariable Long id) throws NoRecordException {
        return new ResponseEntity<>(roomService.getById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> changeRoomInfo(@RequestBody Room room) {
        roomService.changeInfo(room);
        return ResponseBody.successOperation();
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> getAllRooms() {
        return new ResponseEntity<>(roomService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@RequestBody Room room) {
        return new ResponseEntity<>(roomService.create(room), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<RoomDto>> getRoomsFilterByKey(@RequestParam FilterKey key,
                                                             @RequestParam Integer capacity,
                                                             @RequestParam RoomStatus roomStatus) {
        return new ResponseEntity<>(roomService.getRoomsFilterByKey(key, capacity, roomStatus), HttpStatus.OK);
    }

    @GetMapping("/sort")
    public ResponseEntity<List<RoomDto>> getRoomsSortedByKey(@RequestParam SortKey key) {
        return new ResponseEntity<>(roomService.sortByKey(key), HttpStatus.OK);
    }

    @GetMapping("/free/sort")
    public ResponseEntity<List<RoomDto>> getFreeRoomsSortedByKey(@RequestParam SortKey key) {
        return new ResponseEntity<>(roomService.sortFreeByKey(key), HttpStatus.OK);
    }

    @GetMapping("/free/number")
    public ResponseEntity<Integer> getNumberOfFreeRooms() {
        return new ResponseEntity<>(roomService.numberOfFreeRooms(), HttpStatus.OK);
    }

}