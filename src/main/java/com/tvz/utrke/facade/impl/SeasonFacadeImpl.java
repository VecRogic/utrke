package com.tvz.utrke.facade.impl;

import com.tvz.utrke.dto.SeasonDto;
import com.tvz.utrke.facade.SeasonFacade;
import com.tvz.utrke.mapper.SeasonDtoMapper;
import com.tvz.utrke.model.Season;
import com.tvz.utrke.service.jolpicaapi.JolpicaApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SeasonFacadeImpl implements SeasonFacade {


    @Autowired
    JolpicaApiService jolpicaApiService;

    @Autowired
    SeasonDtoMapper seasonDtoMapper;

    public List<SeasonDto> getSeasons() {
        return jolpicaApiService.fetchSeasons() // Mono<List<Season>>
                .map(seasons -> seasons.stream()
                        .map(seasonDtoMapper::map) // Convert each Season to SeasonDto
                        .collect(Collectors.toList())).block(); // Convert Stream to List
    }
}
