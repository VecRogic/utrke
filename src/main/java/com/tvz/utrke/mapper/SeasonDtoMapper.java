package com.tvz.utrke.mapper;

import com.tvz.utrke.dto.SeasonDto;
import com.tvz.utrke.model.Season;
import org.springframework.stereotype.Component;

@Component
public class SeasonDtoMapper extends DtoMapper<Season, SeasonDto> {


    @Override
    public SeasonDto map(Season season) {
        SeasonDto seasonDto = new SeasonDto();

        seasonDto.setSeasonYear(season.getSeason());
        return seasonDto;
    }
}
