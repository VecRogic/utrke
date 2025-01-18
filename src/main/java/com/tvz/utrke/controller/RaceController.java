package com.tvz.utrke.controller;

import com.tvz.utrke.facade.RaceFacade;
import com.tvz.utrke.model.Race;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/races")
public class RaceController {
    private final RaceFacade raceFacade;

    public RaceController(RaceFacade raceFacade) {
        this.raceFacade = raceFacade;
    }

    @GetMapping("/get-all-by-season")
    public List<Race> getBySeason(@RequestParam("season") String season) {
        return raceFacade.getAllRacesBySeason(season);
    }
}
