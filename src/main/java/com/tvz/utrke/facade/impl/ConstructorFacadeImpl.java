package com.tvz.utrke.facade.impl;

import com.tvz.utrke.dto.ConstructorDto;
import com.tvz.utrke.dto.DriverDto;
import com.tvz.utrke.facade.ConstructorFacade;
import com.tvz.utrke.facade.DriverFacade;
import com.tvz.utrke.mapper.ConstructorDtoMapper;
import com.tvz.utrke.service.jolpicaapi.JolpicaApiService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ConstructorFacadeImpl implements ConstructorFacade {

    private final JolpicaApiService jolpicaApiService;

    private final DriverFacade driverFacade;

    private final ConstructorDtoMapper constructorDtoMapper;

    public ConstructorFacadeImpl(JolpicaApiService jolpicaApiService, DriverFacade driverFacade, ConstructorDtoMapper constructorDtoMapper) {
        this.jolpicaApiService = jolpicaApiService;
        this.driverFacade = driverFacade;
        this.constructorDtoMapper = constructorDtoMapper;
    }

    @Override
    public ConstructorDto getConstructorById(String constructorId) {
        return constructorDtoMapper.map(jolpicaApiService.getConstructorById(constructorId));
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
