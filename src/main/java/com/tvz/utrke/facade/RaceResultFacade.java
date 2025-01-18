package com.tvz.utrke.facade;

import com.tvz.utrke.dto.RaceResultDto;
import com.tvz.utrke.model.RaceResult;

import java.util.List;

public interface RaceResultFacade {
    List<RaceResultDto> getRaceResults(String seasonYear, int round);
}
