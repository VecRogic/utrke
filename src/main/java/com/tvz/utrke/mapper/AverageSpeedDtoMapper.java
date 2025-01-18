package com.tvz.utrke.mapper;

import com.tvz.utrke.dto.AverageSpeedDto;
import com.tvz.utrke.model.AverageSpeed;
import org.springframework.stereotype.Component;

@Component
public class AverageSpeedDtoMapper extends DtoMapper<AverageSpeed, AverageSpeedDto> {
    @Override
    public AverageSpeedDto map(AverageSpeed averageSpeed) {
        AverageSpeedDto averageSpeedDto = new AverageSpeedDto();

        averageSpeedDto.setSpeed(averageSpeed.getSpeed());

        return averageSpeedDto;
    }
}
