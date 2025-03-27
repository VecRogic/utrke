package com.tvz.utrke.facade.impl;

import com.tvz.utrke.dto.ConstructorDto;
import com.tvz.utrke.dto.DriverDto;
import com.tvz.utrke.facade.ConstructorFacade;
import com.tvz.utrke.facade.DriverFacade;
import com.tvz.utrke.mapper.ConstructorDtoMapper;
import com.tvz.utrke.service.JolpicaApiService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Component
public class ConstructorFacadeImpl implements ConstructorFacade {

    private final JolpicaApiService jolpicaApiService;

    private final DriverFacade driverFacade;

    private final ConstructorDtoMapper constructorDtoMapper;

    @Override
    public ConstructorDto getConstructorById(String constructorId) {
        return jolpicaApiService.getConstructorById(constructorId)
                .map(constructorDtoMapper::map).block();
    }

    @Override
    public Map<String, Object> getConstructorAndDriversByConstructorId(String constructorId) {
        Map<String, Object> constructorAndDriversByConstructorId = new HashMap<>();

        List<DriverDto> drivers = driverFacade.getDriversByConstructorId(constructorId);
        ConstructorDto constructorDto = getConstructorById(constructorId);

        constructorAndDriversByConstructorId.put("constructor", constructorDto);
        constructorAndDriversByConstructorId.put("drivers", drivers);

        return constructorAndDriversByConstructorId;
    }
}
