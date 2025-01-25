package com.tvz.utrke.facade;

import com.tvz.utrke.dto.ConstructorDto;

import java.util.Map;

public interface ConstructorFacade {

    Map<String, Object> getConstructorAndDriversByConstructorId(String constructorId);

    ConstructorDto getConstructorById(String constructorId);
}
