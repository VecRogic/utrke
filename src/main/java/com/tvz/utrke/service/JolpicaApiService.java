package com.tvz.utrke.service;

import com.tvz.utrke.model.*;
import reactor.core.publisher.Mono;

import java.util.List;

public interface JolpicaApiService {

    Mono<List<Season>> fetchSeasons();

    Mono<List<Race>> fetchRacesBySeason(String seasonYear);

    Mono<List<RaceResult>> fetchRaceResults(String seasonYear, int round);

    Mono<Driver> getDriverById(String driverId);

    Mono<List<Race>> getRacesByDriverId(String driverId);

    Mono<List<Driver>> getDriversByConstructorId(String constructorId);

    Mono<Constructor> getConstructorById(String constructorId);
}
