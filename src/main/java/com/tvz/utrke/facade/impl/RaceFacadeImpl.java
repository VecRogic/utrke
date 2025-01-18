package com.tvz.utrke.facade.impl;

import com.tvz.utrke.facade.RaceFacade;
import com.tvz.utrke.model.Race;
import com.tvz.utrke.service.jolpicaapi.JolpicaApiService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RaceFacadeImpl implements RaceFacade {

    JolpicaApiService jolpicaApiService;

    public RaceFacadeImpl(JolpicaApiService jolpicaApiService) {
        this.jolpicaApiService = jolpicaApiService;
    }

    @Override
    public List<Race> getAllRacesBySeason(String seasonYear) {
        return jolpicaApiService.fetchRacesBySeason(seasonYear);
    }
}
