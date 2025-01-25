package com.tvz.utrke.mapper;

import com.tvz.utrke.dto.DriverDto;
import com.tvz.utrke.model.Driver;
import org.springframework.stereotype.Component;

@Component
public class DriverDtoMapper extends DtoMapper<Driver, DriverDto> {

    @Override
    public DriverDto map(Driver driver) {
        DriverDto driverDto = new DriverDto();

        driverDto.setDriverId(driver.getDriverId());
        driverDto.setCode(driver.getCode());
        driverDto.setUrl(driver.getUrl());
        driverDto.setFirstAndLastName(driver.getGivenName() + " " + driver.getFamilyName());
        driverDto.setFamilyName(driver.getFamilyName());
        driverDto.setDateOfBirth(driver.getDateOfBirth());
        driverDto.setNationality(driver.getNationality());

        return driverDto;
    }

}
