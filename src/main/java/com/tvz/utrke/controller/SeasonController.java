package com.tvz.utrke.controller;

import com.tvz.utrke.dto.SeasonDto;
import com.tvz.utrke.facade.SeasonFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/seasons")
public class SeasonController {
    private final SeasonFacade seasonFacade;

    public SeasonController(SeasonFacade seasonFacade) {
        this.seasonFacade = seasonFacade;
    }

    @GetMapping("/get-all")
    public List<SeasonDto> getSeasons() {
        return seasonFacade.getSeasons();
    }
}


