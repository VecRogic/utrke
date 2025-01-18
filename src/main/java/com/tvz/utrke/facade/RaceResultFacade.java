package com.tvz.utrke.facade;

import com.tvz.utrke.model.RaceResult;

import java.util.List;

public interface RaceResultFacade {
    List<RaceResult> getRaceResults(String seasonYear, int round);
}
