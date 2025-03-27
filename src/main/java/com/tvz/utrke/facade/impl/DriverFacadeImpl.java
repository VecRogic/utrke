package com.tvz.utrke.facade.impl;

import com.tvz.utrke.dto.DriverDto;
import com.tvz.utrke.facade.DriverFacade;
import com.tvz.utrke.mapper.DriverDtoMapper;
import com.tvz.utrke.service.JolpicaApiService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class DriverFacadeImpl implements DriverFacade {

    private final JolpicaApiService jolpicaApiService;

    private final DriverDtoMapper driverDtoMapper;


    @Override
    public List<DriverDto> getDriversByConstructorId(String constructorId) {
        return jolpicaApiService.getDriversByConstructorId(constructorId)
                .map(driver -> driver.stream()
                        .map(driverDtoMapper::map)
                        .collect(Collectors.toList())).block();
    }
}
