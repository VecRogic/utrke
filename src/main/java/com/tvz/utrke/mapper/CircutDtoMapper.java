package com.tvz.utrke.mapper;

import com.tvz.utrke.dto.CircutDto;
import com.tvz.utrke.dto.LocationDto;
import com.tvz.utrke.model.Circut;
import org.springframework.stereotype.Component;

@Component
public class CircutDtoMapper extends DtoMapper<Circut, CircutDto> {

    @Override
    public CircutDto map(Circut circuit) {
        CircutDto circuitDto = new CircutDto();

        circuitDto.setCircuitId(circuit.getCircuitId());
        circuitDto.setUrl(circuit.getUrl());
        circuitDto.setCircuitName(circuit.getCircuitName());

        if (circuit.getLocation() != null) {
            LocationDto locationDto = new LocationDto();
            locationDto.setLocality(circuit.getLocation().getLocality());
            locationDto.setCountry(circuit.getLocation().getCountry());
            circuitDto.setLocation(locationDto);
        }

        return circuitDto;
    }

}
