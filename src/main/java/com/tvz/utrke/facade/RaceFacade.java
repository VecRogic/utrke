package com.tvz.utrke.facade;

import com.tvz.utrke.dto.RaceDto;
import com.tvz.utrke.model.Race;

import java.util.List;

public interface RaceFacade {

    List<RaceDto> getAllRacesBySeason(String seasonYear);
}
