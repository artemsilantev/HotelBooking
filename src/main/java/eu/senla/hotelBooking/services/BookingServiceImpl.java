package eu.senla.hotelBooking.services;

import eu.senla.hotelBooking.api.repositories.BookingRepository;
import eu.senla.hotelBooking.api.repositories.GuestRepository;
import eu.senla.hotelBooking.api.services.BookingService;
import eu.senla.hotelBooking.dto.BookingDto;
import eu.senla.hotelBooking.dto.FacilityDto;
import eu.senla.hotelBooking.exceptions.NoRecordException;
import eu.senla.hotelBooking.model.Booking;
import eu.senla.hotelBooking.model.Facility;
import eu.senla.hotelBooking.model.Guest;
import eu.senla.hotelBooking.model.enums.SortKey;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookingDto create(Booking booking) {
        return modelMapper.map(bookingRepository.save(booking), BookingDto.class);
    }

    @Override
    public BookingDto getById(Long id) throws NoRecordException {
        return modelMapper.map(bookingRepository.findById(id).orElseThrow(() -> new NoRecordException("Booking", id)),
                BookingDto.class);
    }

    @Override
    public List<BookingDto> getAll() {
        return modelMapper.map(bookingRepository.findAll(), new TypeToken<List<BookingDto>>() {}.getType());
    }

    @Override
    public List<FacilityDto> facilitySortedByKey(Long id, SortKey sortKey) throws NoRecordException {
        Booking booking;
        Comparator<Facility> comparator;
        if (bookingRepository.findById(id).isPresent()) {
            booking = bookingRepository.findById(id).get();
        } else {
            throw new NoRecordException("booking", id);
        }
        switch (sortKey) {
            case PRICE:
                comparator = Comparator.comparing(Facility::getPrice);
                break;
            case DATE:
                comparator = Comparator.comparing(Facility::getDateAdded);
                break;
            default:
                return null;
        }
        return modelMapper.map(booking.getFacilities()
                .stream()
                .sorted(comparator)
                .collect(Collectors.toList()),
                new TypeToken<List<FacilityDto>>() {}.getType());
        //
    }

    @Override
    public List<BookingDto> sortByKey(SortKey sortKey) {
        switch (sortKey) {
            case ALPHABET:
                List<Guest> guests = ((List<Guest>) guestRepository.findAll())
                        .stream()
                        .sorted(Comparator.comparing(Guest::getSecondName))
                        .collect(Collectors.toList());
                List<Booking> bookings = new ArrayList<>();
                for (Guest guest : guests) {
                    bookingRepository.findAll()
                            .forEach(booking -> {
                                if (booking.getGuests().contains(guest))
                                    bookings.add(booking);
                            });
                }
                return modelMapper.map(bookings, new TypeToken<List<BookingDto>>() {}.getType());
            case CHECKOUTDATE:
                return modelMapper.map(((List<Booking>) bookingRepository.findAll())
                                .stream()
                                .sorted(Comparator.comparing(Booking::getCheckOutDate))
                                .collect(Collectors.toList()),
                        new TypeToken<List<BookingDto>>() {}.getType());
            default:
                return null;
        }
    }
}
