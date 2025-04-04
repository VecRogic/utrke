package com.tvz.utrke.controller;

import com.tvz.utrke.facade.ConstructorFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/constructors")
public class ConstructorController {

    private final ConstructorFacade constructorFacade;

    @GetMapping("/get-constructor-info")
    public Map<String, Object> getConstructorInfoByConstructorId(@RequestParam("constructorId") String constructorId) {
        return constructorFacade.getConstructorAndDriversByConstructorId(constructorId);
    }
}
