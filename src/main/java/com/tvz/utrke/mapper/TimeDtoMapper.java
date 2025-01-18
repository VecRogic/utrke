package com.tvz.utrke.mapper;

import com.tvz.utrke.dto.TimeDto;
import com.tvz.utrke.model.Time;
import org.springframework.stereotype.Component;

@Component
public class TimeDtoMapper extends DtoMapper<Time, TimeDto> {

    @Override
    public TimeDto map(Time time) {
        TimeDto timeDto = new TimeDto();

        if (time == null) {
            return timeDto;
        }

        timeDto.setTime(time.getTime());

        return timeDto;
    }
}
