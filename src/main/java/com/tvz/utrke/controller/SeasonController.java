package com.tvz.utrke.controller;

import com.tvz.utrke.dto.SeasonDto;
import com.tvz.utrke.facade.SeasonFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/seasons")
public class SeasonController {

    private final SeasonFacade seasonFacade;

    @GetMapping("/get-all")
    public List<SeasonDto> getSeasons() {
        return seasonFacade.getSeasons();
    }
}


