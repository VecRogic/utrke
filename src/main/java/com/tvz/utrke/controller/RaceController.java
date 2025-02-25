package com.tvz.utrke.controller;

import com.tvz.utrke.dto.RaceDto;
import com.tvz.utrke.facade.RaceFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/races")
public class RaceController {
    private final RaceFacade raceFacade;

    public RaceController(RaceFacade raceFacade) {
        this.raceFacade = raceFacade;
    }

    @GetMapping("/get-all-by-season")
    public List<RaceDto> getBySeason(@RequestParam("seasonYear") String seasonYear) {
        return raceFacade.getAllRacesBySeason(seasonYear);
    }

    @GetMapping("/get-by-driver-id")
    public Map<String, Object> getByDriver(@RequestParam("driverId") String driverId) {
        return raceFacade.getRacesByDriverId(driverId);
    }
}
