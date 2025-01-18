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
        driverDto.setDriverFirstAndLastName(driver.getGivenName() + ' ' + driver.getFamilyName());

        return driverDto;
    }
}
