package com.tvz.utrke.service.jolpicaapi;

import com.tvz.utrke.model.Race;
import com.tvz.utrke.model.Season;

import java.util.List;

public interface JolpicaApiService {

    List<Season> fetchSeasons();

    List<Race> fetchRacesBySeason(String seasonYear);
}
