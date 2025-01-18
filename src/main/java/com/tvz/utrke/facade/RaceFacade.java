package com.tvz.utrke.facade;

import com.tvz.utrke.model.Race;

import java.util.List;

public interface RaceFacade {

    List<Race> getAllRacesBySeason(String seasonYear);
}
