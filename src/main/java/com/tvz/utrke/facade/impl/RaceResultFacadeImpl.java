package com.tvz.utrke.facade.impl;

import com.tvz.utrke.facade.RaceResultFacade;
import com.tvz.utrke.model.RaceResult;
import com.tvz.utrke.service.jolpicaapi.JolpicaApiService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RaceResultFacadeImpl implements RaceResultFacade {

    private final JolpicaApiService jolpicaApiService;

    public RaceResultFacadeImpl(JolpicaApiService jolpicaApiService) {
        this.jolpicaApiService = jolpicaApiService;
    }


    @Override
    public List<RaceResult> getRaceResults(String seasonYear, int round) {
        return jolpicaApiService.fetchRaceResults(seasonYear, round);
    }
}
