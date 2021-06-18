package eu.senla.hotelBooking.services;

import eu.senla.hotelBooking.api.repositories.FacilityRepository;
import eu.senla.hotelBooking.api.services.FacilityService;
import eu.senla.hotelBooking.dto.FacilityDto;
import eu.senla.hotelBooking.exceptions.NoRecordException;
import eu.senla.hotelBooking.model.Facility;
import eu.senla.hotelBooking.model.enums.SortKey;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FacilityDto create(Facility entity) {
        for (Facility facility : facilityRepository.findAll()) {
            if (facility.getPrice() == entity.getPrice() &&
                    facility.getName().equals(entity.getName())) {
                entity.setId(facility.getId());
                return modelMapper.map(facility, FacilityDto.class);
            }
        }
        entity.setId(facilityRepository.save(entity).getId());
        return modelMapper.map(entity, FacilityDto.class);
    }

    @Override
    public FacilityDto getById(Long id) throws NoRecordException {
        return modelMapper.map(facilityRepository.findById(id).orElseThrow(() -> new NoRecordException("Facility", id)),
                FacilityDto.class);
    }

    @Override
    public List<FacilityDto> getAll() {
        return modelMapper.map(facilityRepository.findAll(), new TypeToken<List<FacilityDto>>(){}.getType());
    }

    @Override
    public void changeInfo(Facility facility) {
        facilityRepository.save(facility);
    }

    @Override
    public List<FacilityDto> sortByKey(SortKey sortKey) {
        switch (sortKey) {
            case PRICE:
                return modelMapper.map(((List<Facility>) facilityRepository.findAll())
                        .stream()
                        .sorted(Comparator.comparing(Facility::getPrice))
                        .collect(Collectors.toList()),
                        new TypeToken<List<FacilityDto>>(){}.getType());
            default:
                return null;
        }
    }

}
