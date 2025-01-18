package com.tvz.utrke.mapper;

import com.tvz.utrke.dto.RaceDto;
import com.tvz.utrke.model.Race;
import org.springframework.stereotype.Component;

@Component
public class RaceDtoMapper extends Mapper<Race, RaceDto>{

    @Override
    public RaceDto map(Race race) {
        RaceDto raceDto = new RaceDto();

        raceDto.setRaceName(race.getRaceName());
        raceDto.setRound(race.getRound());

        return raceDto;
    }
}
