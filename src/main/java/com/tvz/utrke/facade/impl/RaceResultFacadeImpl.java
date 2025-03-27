package com.tvz.utrke.facade.impl;

import com.tvz.utrke.dto.RacePredictionDto;
import com.tvz.utrke.dto.RaceResultDto;
import com.tvz.utrke.facade.RaceResultFacade;
import com.tvz.utrke.mapper.RaceResultDtoMapper;
import com.tvz.utrke.service.JolpicaApiService;
import com.tvz.utrke.service.PerplexityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class RaceResultFacadeImpl implements RaceResultFacade {

    private final JolpicaApiService jolpicaApiService;

    private final RaceResultDtoMapper raceResultDtoMapper;

    private final PerplexityService perplexityService;

    @Override
    public List<RaceResultDto> getRaceResults(String seasonYear, int round) {
        return jolpicaApiService.fetchRaceResults(seasonYear, round) // Mono<List<Season>>
                .map(raceResults -> raceResults.stream()
                        .map(raceResultDtoMapper::map) // Convert each Season to SeasonDto
                        .collect(Collectors.toList())).block();
    }

    @Override
    public RacePredictionDto getRacePrediction(String race) {
        return perplexityService.getGeneratedRacePrediction(race);
    }
}
