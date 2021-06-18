package eu.senla.hotelBooking.services;


import eu.senla.hotelBooking.api.repositories.RoomRepository;
import eu.senla.hotelBooking.api.services.RoomService;
import eu.senla.hotelBooking.dto.RoomDto;
import eu.senla.hotelBooking.exceptions.NoRecordException;
import eu.senla.hotelBooking.model.Room;
import eu.senla.hotelBooking.model.enums.FilterKey;
import eu.senla.hotelBooking.model.enums.RoomStatus;
import eu.senla.hotelBooking.model.enums.SortKey;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RoomDto create(Room entity) {
        return modelMapper.map(roomRepository.save(entity), RoomDto.class);
    }

    @Override
    public RoomDto getById(Long id) throws NoRecordException {
        return modelMapper.map(roomRepository.findById(id).orElseThrow(() -> new NoRecordException("Room", id)),
                RoomDto.class);
    }

    @Override
    public List<RoomDto> getAll() {
        return modelMapper.map(roomRepository.findAll(), new TypeToken<List<RoomDto>>(){}.getType());
    }

    @Override
    public void changeInfo(Room room) {
        roomRepository.save(room);
    }

    @Override
    public Integer numberOfFreeRooms() {
        return roomRepository.getRoomsByStatusEquals(RoomStatus.AVAILABLE).size();
    }

    @Override
    public List<RoomDto> getRoomsFilterByKey(FilterKey filterKey, Integer capacity, RoomStatus roomStatus) {
        Predicate<Room> predicate = getFilter(filterKey, capacity, roomStatus);
        if (predicate == null) {
            return null;
        }
        return modelMapper.map(((List<Room>) roomRepository.findAll())
                .stream()
                .filter(predicate)
                .collect(Collectors.toList()),
                new TypeToken<List<RoomDto>>(){}.getType());
    }

    @Override
    public List<RoomDto> sortFreeByKey(SortKey sortKey) {
        Comparator<Room> comparator = getComparator(sortKey);
        if (comparator == null) {
            return null;
        }
        return modelMapper.map(roomRepository.getRoomsByStatusEquals(RoomStatus.AVAILABLE)
                .stream()
                .sorted(comparator)
                .collect(Collectors.toList()),
                new TypeToken<List<RoomDto>>(){}.getType());
    }

    @Override
    public List<RoomDto> sortByKey(SortKey sortKey) {
        Comparator<Room> comparator = getComparator(sortKey);
        if (comparator == null) {
            return null;
        }
        return modelMapper.map(((List<Room>) roomRepository.findAll())
                .stream()
                .sorted(comparator)
                .collect(Collectors.toList()),
                new TypeToken<List<RoomDto>>(){}.getType());
    }

    private Predicate<Room> getFilter(FilterKey filterKey, int capacity, RoomStatus roomStatus) {
        switch (filterKey) {
            case CAPACITY:
                return room -> room.getCapacity() >= capacity;
            case ROOMSTATUS:
                return room -> room.getStatus() == roomStatus;
            default:
                return null;
        }
    }

    private Comparator<Room> getComparator(SortKey sortKey) {
        switch (sortKey) {
            case PRICE:
                return Comparator.comparing(Room::getPrice);
            case CAPACITY:
                return Comparator.comparing(Room::getCapacity);
            case STARS:
                return Comparator.comparing(Room::getStars);
            default:
                return null;
        }
    }

}