package ua.in.lbn.sb2.dto;

import java.util.StringJoiner;

public class LocationDto {

    private Long id;
    private Long zip;
    private String city;

    @SuppressWarnings("unused")
    protected LocationDto() {
        // JSON
    }

    @SuppressWarnings("unused")
    protected LocationDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getZip() {
        return zip;
    }

    public void setZip(Long zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LocationDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("zip=" + zip)
                .add("city='" + city + "'")
                .toString();
    }
}
