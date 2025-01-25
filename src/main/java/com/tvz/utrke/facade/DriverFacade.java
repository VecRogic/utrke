package com.tvz.utrke.facade;

import com.tvz.utrke.dto.DriverDto;

import java.util.List;

public interface DriverFacade {

    List<DriverDto> getDriversByConstructorId(String constructorId);
}
