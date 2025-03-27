package com.tvz.utrke.facade.impl;

import com.tvz.utrke.dto.SeasonDto;
import com.tvz.utrke.facade.SeasonFacade;
import com.tvz.utrke.mapper.SeasonDtoMapper;
import com.tvz.utrke.service.JolpicaApiService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class SeasonFacadeImpl implements SeasonFacade {

    private final JolpicaApiService jolpicaApiService;

    private final SeasonDtoMapper seasonDtoMapper;

    public List<SeasonDto> getSeasons() {
        return jolpicaApiService.fetchSeasons()
                .map(seasons -> seasons.stream()
                        .map(seasonDtoMapper::map)
                        .collect(Collectors.toList())).block();
    }
}
