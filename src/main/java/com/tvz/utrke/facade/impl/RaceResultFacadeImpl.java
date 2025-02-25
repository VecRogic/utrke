package com.tvz.utrke.facade.impl;

import com.tvz.utrke.dto.RaceResultDto;
import com.tvz.utrke.facade.RaceResultFacade;
import com.tvz.utrke.mapper.RaceResultDtoMapper;
import com.tvz.utrke.model.RaceResult;
import com.tvz.utrke.service.jolpicaapi.JolpicaApiService;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RaceResultFacadeImpl implements RaceResultFacade {

    private final JolpicaApiService jolpicaApiService;

    private final RaceResultDtoMapper raceResultDtoMapper;

    public RaceResultFacadeImpl(JolpicaApiService jolpicaApiService, RaceResultDtoMapper raceResultDtoMapper) {
        this.jolpicaApiService = jolpicaApiService;
        this.raceResultDtoMapper = raceResultDtoMapper;
    }


    @Override
    public List<RaceResultDto> getRaceResults(String seasonYear, int round) {
        return jolpicaApiService.fetchRaceResults(seasonYear, round) // Mono<List<Season>>
                .map(raceResults -> raceResults.stream()
                        .map(raceResultDtoMapper::map) // Convert each Season to SeasonDto
                        .collect(Collectors.toList())).block();
    }
}
