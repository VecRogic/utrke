package com.tvz.utrke.mapper;

import com.tvz.utrke.dto.LocationDto;
import com.tvz.utrke.model.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationDtoMapper extends DtoMapper<Location, LocationDto> {

    @Override
    public LocationDto map(Location location) {
        LocationDto locationDto = new LocationDto();
        locationDto.setLocality(location.getLocality());
        locationDto.setCountry(location.getCountry());
        return locationDto;
    }

}
