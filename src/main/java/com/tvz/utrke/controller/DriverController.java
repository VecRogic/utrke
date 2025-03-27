package com.tvz.utrke.controller;

import com.tvz.utrke.dto.DriverDto;
import com.tvz.utrke.facade.DriverFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/drivers")
public class DriverController {

    private final DriverFacade driverFacade;

    @GetMapping("/get-by-constructor-id")
    public List<DriverDto> getByDriver(@RequestParam("constructorId") String constructorId) {
        return driverFacade.getDriversByConstructorId(constructorId);
    }
}
