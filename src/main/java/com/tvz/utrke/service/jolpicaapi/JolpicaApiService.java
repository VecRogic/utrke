package com.tvz.utrke.service.jolpicaapi;

import com.tvz.utrke.model.*;

import java.util.List;

public interface JolpicaApiService {

    List<Season> fetchSeasons();

    List<Race> fetchRacesBySeason(String seasonYear);

    List<RaceResult> fetchRaceResults(String seasonYear, int round);

    Driver getDriverById(String driverId);

    List<Race> getRacesByDriverId(String driverId);

    List<Driver> getDriversByConstructorId(String constructorId);

    Constructor getConstructorById(String constructorId);
}
