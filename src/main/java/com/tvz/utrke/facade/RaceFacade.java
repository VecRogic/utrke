package com.tvz.utrke.facade;

import com.tvz.utrke.dto.RaceDto;

import java.util.List;
import java.util.Map;

public interface RaceFacade {

    List<RaceDto> getAllRacesBySeason(String seasonYear);

    Map<String, Object> getRacesByDriverId(String driverId);
}
