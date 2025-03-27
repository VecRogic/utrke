package com.tvz.utrke.controller;

import com.tvz.utrke.dto.RacePredictionDto;
import com.tvz.utrke.dto.RaceResultDto;
import com.tvz.utrke.facade.RaceResultFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/race-results")
public class RaceResultController {
    private final RaceResultFacade raceResultFacade;

    @GetMapping("/get-by-season-and-round")
    public List<RaceResultDto> getRaceResults(@RequestParam("seasonYear") String season, @RequestParam("round") int round) {
        return raceResultFacade.getRaceResults(season, round);
    }

    @GetMapping("/prediction")
    public RacePredictionDto getRacePrediction(@RequestParam("race") String race) {
        return raceResultFacade.getRacePrediction(race);
    }
}
