package ua.in.lbn.sb2.dto;

import java.util.Set;
import java.util.StringJoiner;

public class PersonDto {

    private String name;
    private Set<LocationDto> locations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<LocationDto> getLocations() {
        return locations;
    }

    public void setLocations(Set<LocationDto> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PersonDto.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("locations=" + locations)
                .toString();
    }
}
