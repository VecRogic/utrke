package com.tvz.utrke.controller;

import com.tvz.utrke.dto.SeasonDto;
import com.tvz.utrke.facade.RaceResultFacade;
import com.tvz.utrke.model.RaceResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/race-results")
public class RaceResultController {
    private final RaceResultFacade raceResultFacade;

    public RaceResultController(RaceResultFacade raceResultFacade) {
        this.raceResultFacade = raceResultFacade;
    }

    @GetMapping("/get-by-season-and-round")
    public List<RaceResult> getRaceResults(@RequestParam("seasonYear") String season, @RequestParam("round") int round) {
        return raceResultFacade.getRaceResults(season, round);
    }
}
