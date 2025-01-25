package com.tvz.utrke.facade.impl;

import com.tvz.utrke.dto.DriverDto;
import com.tvz.utrke.facade.DriverFacade;
import com.tvz.utrke.mapper.DriverDtoMapper;
import com.tvz.utrke.model.Driver;
import com.tvz.utrke.service.jolpicaapi.JolpicaApiService;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DriverFacadeImpl implements DriverFacade {

    private final JolpicaApiService jolpicaApiService;

    private final DriverDtoMapper driverDtoMapper;

    public DriverFacadeImpl(JolpicaApiService jolpicaApiService, DriverDtoMapper driverDtoMapper) {
        this.jolpicaApiService = jolpicaApiService;
        this.driverDtoMapper = driverDtoMapper;
    }


    @Override
    public List<DriverDto> getDriversByConstructorId(String constructorId) {
        List<Driver> drivers = jolpicaApiService.getDriversByConstructorId(constructorId);

        drivers.sort(Comparator.comparing(Driver::getGivenName));

        return drivers.stream()
                .map(driverDtoMapper::map)
                .collect(Collectors.toList());
    }
}
