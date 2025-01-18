package com.tvz.utrke.facade.impl;

import com.tvz.utrke.dto.RaceDto;
import com.tvz.utrke.facade.RaceFacade;
import com.tvz.utrke.mapper.RaceDtoMapper;
import com.tvz.utrke.model.Race;
import com.tvz.utrke.service.jolpicaapi.JolpicaApiService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RaceFacadeImpl implements RaceFacade {

    private final JolpicaApiService jolpicaApiService;

    private final RaceDtoMapper raceDtoMapper;

    public RaceFacadeImpl(JolpicaApiService jolpicaApiService, RaceDtoMapper raceDtoMapper) {
        this.jolpicaApiService = jolpicaApiService;
        this.raceDtoMapper = raceDtoMapper;
    }

    @Override
    public List<RaceDto> getAllRacesBySeason(String seasonYear) {
        List<Race> races = jolpicaApiService.fetchRacesBySeason(seasonYear);

        return races.stream()
                .map(raceDtoMapper::map)
                .collect(Collectors.toList());
    }
}
