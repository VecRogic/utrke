package com.tvz.utrke.service.jolpicaapi;

import com.tvz.utrke.model.Driver;
import com.tvz.utrke.model.Race;
import com.tvz.utrke.model.RaceResult;
import com.tvz.utrke.model.Season;

import java.util.List;

public interface JolpicaApiService {

    List<Season> fetchSeasons();

    List<Race> fetchRacesBySeason(String seasonYear);

    List<RaceResult> fetchRaceResults(String seasonYear, int round);

    Driver getDriverById(String driverId);

    List<Race> getRacesByDriverId(String driverId);
}
