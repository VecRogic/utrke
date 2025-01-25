package com.tvz.utrke.mapper;

import com.tvz.utrke.dto.ConstructorDto;
import com.tvz.utrke.model.Constructor;
import org.springframework.stereotype.Component;

@Component
public class ConstructorDtoMapper extends DtoMapper<Constructor, ConstructorDto> {

    @Override
    public ConstructorDto map(Constructor constructor) {
        ConstructorDto constructorDto = new ConstructorDto();

        constructorDto.setConstructorId(constructor.getConstructorId());
        constructorDto.setUrl(constructor.getUrl());
        constructorDto.setNationality(constructor.getNationality());
        constructorDto.setName(constructor.getName());

        return constructorDto;
    }
}
