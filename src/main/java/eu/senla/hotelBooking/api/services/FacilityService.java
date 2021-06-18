package eu.senla.hotelBooking.api.services;

import eu.senla.hotelBooking.dto.FacilityDto;
import eu.senla.hotelBooking.exceptions.NoRecordException;
import eu.senla.hotelBooking.model.Facility;
import eu.senla.hotelBooking.model.enums.SortKey;

import java.util.List;

public interface FacilityService{

    FacilityDto create(Facility entity);

    FacilityDto getById(Long id) throws NoRecordException;

    List<FacilityDto> getAll();

    void changeInfo(Facility facility);

    List<FacilityDto> sortByKey(SortKey sortKey);

}
