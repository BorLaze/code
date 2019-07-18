package ua.in.lbn.sb2.mapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

import ua.in.lbn.sb2.domain.Location;
import ua.in.lbn.sb2.domain.Person;
import ua.in.lbn.sb2.dto.LocationDto;
import ua.in.lbn.sb2.dto.PersonDto;
import ua.in.lbn.sb2.service.PersonService;

import static com.google.common.collect.Sets.newHashSet;

@Component
public class PersonMapper {

    private final ModelMapper modelMapper;
    private final LocationMapper locationMapper;
    private final PersonService personService;

    public PersonMapper(ModelMapper modelMapper, LocationMapper locationMapper, PersonService personService) {
        this.modelMapper = modelMapper;
        this.locationMapper = locationMapper;
        this.personService = personService;

        setupPersonMapping();
    }

    public Person convert(PersonDto personDto) {
        return modelMapper.map(personDto, Person.class);
    }

    public Person convert(long id, PersonDto personDto) {
        Person person = personService.read(id)
                .orElseThrow(
                        () -> new IllegalArgumentException(String.format("No Person with id = %d", id))
                );
        modelMapper.map(personDto, person);

        return person;
    }

    private void setupPersonMapping() {
        modelMapper.createTypeMap(PersonDto.class, Person.class)
                .addMappings(
                        mapper -> mapper
                                .using(getDtosToLocationsConverter())
                                .map(PersonDto::getLocations, Person::setLocations)
                );
    }

    private Converter<Set<LocationDto>, Set<Location>> getDtosToLocationsConverter() {
        return ctx -> {
            Set<LocationDto> source = ctx.getSource();

            return (source == null)
                    ? newHashSet()
                    : source.stream()
                    .map(locationMapper::convert)
                    .collect(Collectors.toSet());
        };
    }

}
