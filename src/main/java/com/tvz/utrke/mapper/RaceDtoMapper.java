package com.tvz.utrke.mapper;

import com.tvz.utrke.dto.RaceDto;
import com.tvz.utrke.model.Race;
import org.springframework.stereotype.Component;

@Component
public class RaceDtoMapper extends DtoMapper<Race, RaceDto> {

    private final CircutDtoMapper circutDtoMapper;

    public RaceDtoMapper(CircutDtoMapper circutDtoMapper) {
        this.circutDtoMapper = circutDtoMapper;
    }

    @Override
    public RaceDto map(Race race) {
        RaceDto raceDto = new RaceDto();

        raceDto.setSeason(race.getSeason());
        raceDto.setRound(race.getRound());
        raceDto.setUrl(race.getUrl());
        raceDto.setRaceName(race.getRaceName());
        raceDto.setCircuit(circutDtoMapper.map(race.getCircuit()));
        raceDto.setDate(race.getDate());

        return raceDto;
    }

}
