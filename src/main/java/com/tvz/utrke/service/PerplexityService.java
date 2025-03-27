package com.tvz.utrke.service;

import com.tvz.utrke.dto.RacePredictionDto;

public interface PerplexityService {
    RacePredictionDto getGeneratedRacePrediction(String race);
}
