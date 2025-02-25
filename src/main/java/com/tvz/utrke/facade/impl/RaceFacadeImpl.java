package com.tvz.utrke.facade.impl;

import com.tvz.utrke.dto.DriverDto;
import com.tvz.utrke.dto.RaceDto;
import com.tvz.utrke.dto.SeasonDto;
import com.tvz.utrke.facade.RaceFacade;
import com.tvz.utrke.mapper.DriverDtoMapper;
import com.tvz.utrke.mapper.RaceDtoMapper;
import com.tvz.utrke.model.Race;
import com.tvz.utrke.service.jolpicaapi.JolpicaApiService;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Comparator;
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
        return jolpicaApiService.fetchRacesBySeason(seasonYear) // Mono<List<Season>>
                .map(races -> races.stream()
                        .map(raceDtoMapper::map) // Convert each Season to SeasonDto
                        .collect(Collectors.toList())).block(); // Convert Stream to List
    }

    @Override
    public Map<String, Object> getRacesByDriverId(String driverId) {
        Map<String, Object> driverDtoRaceDtoMap = new HashMap<>();

        DriverDto driverDto = driverDtoMapper.map(jolpicaApiService.getDriverById(driverId).block());

        List<RaceDto> races = jolpicaApiService.getRacesByDriverId(driverId)
                .map(race -> race.stream()
                        .map(raceDtoMapper::map)
                        .collect(Collectors.toList())).block();

        driverDtoRaceDtoMap.put("driver", driverDto);
        driverDtoRaceDtoMap.put("races", races);

        return driverDtoRaceDtoMap;
    }
}
