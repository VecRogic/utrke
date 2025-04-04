package com.tvz.utrke.facade;

import com.tvz.utrke.dto.RacePredictionDto;
import com.tvz.utrke.dto.RaceResultDto;

import java.util.List;

public interface RaceResultFacade {
    List<RaceResultDto> getRaceResults(String seasonYear, int round);
    RacePredictionDto getRacePrediction(String race);
}
