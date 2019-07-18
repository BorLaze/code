package ua.in.lbn.sb2.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import ua.in.lbn.sb2.domain.Location;
import ua.in.lbn.sb2.dto.LocationDto;
import ua.in.lbn.sb2.repository.LocationRepository;

@Component
public class LocationMapper {

    private final ModelMapper modelMapper;
    private final LocationRepository locationRepository;

    public LocationMapper(ModelMapper modelMapper, LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
        this.modelMapper = modelMapper;
    }

    Location convert(LocationDto locationDto) {
        if (locationDto.getId() != null) {
            Location location = locationRepository.findById(locationDto.getId())
                    .orElseThrow(
                            () -> new IllegalArgumentException(String.format("No Location with id = %d", locationDto.getId()))
                    );

            modelMapper.map(locationDto, location);
            return location;
        }

        return modelMapper.map(locationDto, Location.class);
    }
}
