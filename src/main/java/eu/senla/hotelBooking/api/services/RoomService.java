package eu.senla.hotelBooking.api.services;

import eu.senla.hotelBooking.dto.RoomDto;
import eu.senla.hotelBooking.exceptions.NoRecordException;
import eu.senla.hotelBooking.model.Room;
import eu.senla.hotelBooking.model.enums.FilterKey;
import eu.senla.hotelBooking.model.enums.RoomStatus;
import eu.senla.hotelBooking.model.enums.SortKey;

import java.util.List;

public interface RoomService {

    RoomDto create(Room entity);

    RoomDto getById(Long id) throws NoRecordException;

    List<RoomDto> getAll();

    void changeInfo(Room room);

    List<RoomDto> getRoomsFilterByKey(FilterKey filterKey, Integer capacity, RoomStatus roomStatus);

    List<RoomDto> sortFreeByKey(SortKey sortKey);

    List<RoomDto> sortByKey(SortKey sortKey);

    Integer numberOfFreeRooms();

}
