package com.tvz.utrke.facade.impl;

import com.tvz.utrke.dto.DriverDto;
import com.tvz.utrke.dto.RaceDto;
import com.tvz.utrke.facade.RaceFacade;
import com.tvz.utrke.mapper.DriverDtoMapper;
import com.tvz.utrke.mapper.RaceDtoMapper;
import com.tvz.utrke.model.Race;
import com.tvz.utrke.service.jolpicaapi.JolpicaApiService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RaceFacadeImpl implements RaceFacade {

    private final JolpicaApiService jolpicaApiService;

    private final RaceDtoMapper raceDtoMapper;

    private final DriverDtoMapper driverDtoMapper;

    public RaceFacadeImpl(JolpicaApiService jolpicaApiService, RaceDtoMapper raceDtoMapper, DriverDtoMapper driverDtoMapper) {
        this.jolpicaApiService = jolpicaApiService;
        this.raceDtoMapper = raceDtoMapper;
        this.driverDtoMapper = driverDtoMapper;
    }

    @Override
    public List<RaceDto> getAllRacesBySeason(String seasonYear) {
        List<Race> races = jolpicaApiService.fetchRacesBySeason(seasonYear);

        return races.stream()
                .map(raceDtoMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> getRacesByDriverId(String driverId) {
        Map<String, Object> driverDtoRaceDtoMap = new HashMap<>();

        DriverDto driverDto = driverDtoMapper.map(jolpicaApiService.getDriverById(driverId));

        List<RaceDto> races = jolpicaApiService.getRacesByDriverId(driverId)
                .stream()
                .map(raceDtoMapper::map)
                .collect(Collectors.toList());

        driverDtoRaceDtoMap.put("driver", driverDto);
        driverDtoRaceDtoMap.put("races", races);

        return driverDtoRaceDtoMap;
    }
}
