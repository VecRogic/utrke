package com.tvz.utrke.facade;

import com.tvz.utrke.dto.SeasonDto;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface SeasonFacade {
    List<SeasonDto> getSeasons();
}
