package com.tvz.utrke.mapper;

import com.tvz.utrke.dto.RaceResultDto;
import com.tvz.utrke.model.RaceResult;
import org.springframework.stereotype.Component;

@Component
public class RaceResultDtoMapper extends DtoMapper<RaceResult, RaceResultDto> {

    private final DriverDtoMapper driverDtoMapper;

    private final ConstructorDtoMapper constructorDtoMapper;

    private final TimeDtoMapper timeDtoMapper;

    private final AverageSpeedDtoMapper averageSpeedDtoMapper;

    public RaceResultDtoMapper(DriverDtoMapper driverDtoMapper, ConstructorDtoMapper constructorDtoMapper, TimeDtoMapper timeDtoMapper, AverageSpeedDtoMapper averageSpeedDtoMapper) {
        this.driverDtoMapper = driverDtoMapper;
        this.constructorDtoMapper = constructorDtoMapper;
        this.timeDtoMapper = timeDtoMapper;
        this.averageSpeedDtoMapper = averageSpeedDtoMapper;
    }


    @Override
    public RaceResultDto map(RaceResult raceResult) {
        RaceResultDto raceResultDto = new RaceResultDto();

        raceResultDto.setNumber(raceResult.getNumber());
        raceResultDto.setPosition(raceResult.getPosition());
        raceResultDto.setPoints(raceResultDto.getPoints());
        raceResultDto.setDriver(driverDtoMapper.map(raceResult.getDriver()));
        raceResultDto.setConstructor(constructorDtoMapper.map(raceResult.getConstructor()));
        raceResultDto.setTime(timeDtoMapper.map(raceResult.getTime()));
        raceResultDto.setAverageSpeed(averageSpeedDtoMapper.map(raceResult.getFastestLap().getAverageSpeed()));

        return raceResultDto;
    }
}
