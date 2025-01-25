package com.tvz.utrke.service.jolpicaapi.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvz.utrke.model.*;
import com.tvz.utrke.service.jolpicaapi.JolpicaApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class JolpicaApiServiceImpl implements JolpicaApiService {

    private final RestTemplate restTemplate;

    @Value("${app.jolpica.url}")
    private String jolpicaUrl;

    public JolpicaApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public List<Season> fetchSeasons() {
        String url = jolpicaUrl + "/seasons?limit=76";
        String response = restTemplate.getForObject(url, String.class);

        List<Season> seasons = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response);
            JsonNode seasonsNode = root.path("MRData").path("SeasonTable").path("Seasons");

            seasonsNode.forEach(node -> {
                Season season = new Season();
                season.setSeason(node.get("season").asText());
                season.setUrl(node.get("url").asText());
                seasons.add(season);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return seasons;
    }

    public List<Race> fetchRacesBySeason(String seasonYear) {
        String url = jolpicaUrl + "/" + seasonYear + "/races";
        String response = restTemplate.getForObject(url, String.class);

        List<Race> races = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response);
            JsonNode racesNode = root.path("MRData").path("RaceTable").path("Races");

            racesNode.forEach(node -> {
                Race race = new Race();
                race.setSeason(node.get("season").asText());
                race.setRound(node.get("round").asText());
                race.setUrl(node.get("url").asText());
                race.setRaceName(node.get("raceName").asText());

                // Parsing the Circuit object
                JsonNode circuitNode = node.get("Circuit");
                if (circuitNode != null) {
                    Circut circuit = new Circut();
                    circuit.setCircuitId(circuitNode.get("circuitId").asText());
                    circuit.setUrl(circuitNode.get("url").asText());
                    circuit.setCircuitName(circuitNode.get("circuitName").asText());

                    // Parsing the Location object
                    JsonNode locationNode = circuitNode.get("Location");
                    if (locationNode != null) {
                        Location location = new Location();
                        location.setLat(locationNode.get("lat").asText());
                        location.setLongitude(locationNode.get("long").asText());
                        location.setLocality(locationNode.get("locality").asText());
                        location.setCountry(locationNode.get("country").asText());

                        // Set the location in the circuit
                        circuit.setLocation(location);
                    }

                    // Set the circuit in the race
                    race.setCircuit(circuit);
                }

                // Set the date in the race
                race.setDate(node.get("date").asText());

                // Add the race to the list
                races.add(race);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return races;
    }

    @Override
    public List<RaceResult> fetchRaceResults(String seasonYear, int round) {
        String url = jolpicaUrl + "/" + seasonYear + "/" + round + "/results";
        String response = restTemplate.getForObject(url, String.class);

        List<RaceResult> results = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response);
            JsonNode resultsNode = root.path("MRData").path("RaceTable").path("Races").get(0).path("Results");

            resultsNode.forEach(resultNode -> {
                RaceResult result = new RaceResult();

                result.setNumber(resultNode.get("number").asText());
                result.setPosition(resultNode.get("position").asText());
                result.setPositionText(resultNode.get("positionText").asText());
                result.setPoints(resultNode.get("points").asText());

                // Parsing the Driver object
                JsonNode driverNode = resultNode.get("Driver");
                if (driverNode != null) {
                    Driver driver = new Driver();
                    driver.setDriverId(driverNode.get("driverId").asText());
                    driver.setPermanentNumber(driverNode.get("permanentNumber").asText());
                    driver.setCode(driverNode.get("code").asText());
                    driver.setUrl(driverNode.get("url").asText());
                    driver.setGivenName(driverNode.get("givenName").asText());
                    driver.setFamilyName(driverNode.get("familyName").asText());
                    driver.setDateOfBirth(driverNode.get("dateOfBirth").asText());
                    driver.setNationality(driverNode.get("nationality").asText());

                    result.setDriver(driver);
                }

                // Parsing the Constructor object
                JsonNode constructorNode = resultNode.get("Constructor");
                if (constructorNode != null) {
                    Constructor constructor = new Constructor();
                    constructor.setConstructorId(constructorNode.get("constructorId").asText());
                    constructor.setUrl(constructorNode.get("url").asText());
                    constructor.setName(constructorNode.get("name").asText());
                    constructor.setNationality(constructorNode.get("nationality").asText());

                    result.setConstructor(constructor);
                }

                // Parsing the Time object
                JsonNode timeNode = resultNode.get("Time");
                if (timeNode != null) {
                    Time time = new Time();
                    time.setMillis(timeNode.get("millis").asText());
                    time.setTime(timeNode.get("time").asText());

                    result.setTime(time);
                }

                // Parsing the FastestLap object
                JsonNode fastestLapNode = resultNode.get("FastestLap");
                if (fastestLapNode != null) {
                    FastestLap fastestLap = new FastestLap();
                    fastestLap.setRank(fastestLapNode.get("rank").asText());
                    fastestLap.setLap(fastestLapNode.get("lap").asText());

                    // Parsing the FastestLap Time object
                    JsonNode fastestLapTimeNode = fastestLapNode.get("Time");
                    if (fastestLapTimeNode != null) {
                        Time fastestLapTime = new Time();
                        fastestLapTime.setTime(fastestLapTimeNode.get("time").asText());
                        fastestLap.setTime(fastestLapTime);
                    }

                    // Parsing the AverageSpeed object
                    JsonNode avgSpeedNode = fastestLapNode.get("AverageSpeed");
                    if (avgSpeedNode != null) {
                        AverageSpeed averageSpeed = new AverageSpeed();
                        averageSpeed.setUnits(avgSpeedNode.get("units").asText());
                        averageSpeed.setSpeed(avgSpeedNode.get("speed").asText());

                        fastestLap.setAverageSpeed(averageSpeed);
                    }

                    // Set the fastest lap in the result
                    result.setFastestLap(fastestLap);
                }

                // Add the result to the list
                results.add(result);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    @Override
    public Driver getDriverById(String driverId) {
        String url = jolpicaUrl + "/drivers/" + driverId;
        String response = restTemplate.getForObject(url, String.class);

        Driver driver = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response);
            JsonNode driversNode = root.path("MRData").path("DriverTable").path("Drivers");

            if (driversNode.isArray() && driversNode.size() > 0) {
                JsonNode driverNode = driversNode.get(0);
                driver = new Driver();

                driver.setDriverId(driverNode.get("driverId").asText());
                driver.setPermanentNumber(driverNode.get("permanentNumber").asText());
                driver.setCode(driverNode.get("code").asText());
                driver.setUrl(driverNode.get("url").asText());
                driver.setGivenName(driverNode.get("givenName").asText());
                driver.setFamilyName(driverNode.get("familyName").asText());
                driver.setDateOfBirth(driverNode.get("dateOfBirth").asText());
                driver.setNationality(driverNode.get("nationality").asText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return driver;
    }

    @Override
    public List<Race> getRacesByDriverId(String driverId) {
        String url = jolpicaUrl + "/drivers/" + driverId + "/races/";
        String response = restTemplate.getForObject(url, String.class);

        List<Race> races = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response);
            JsonNode racesNode = root.path("MRData").path("RaceTable").path("Races");

            if (racesNode.isArray()) {
                racesNode.forEach(node -> {
                    Race race = new Race();
                    race.setSeason(node.get("season").asText());
                    race.setRound(node.get("round").asText());
                    race.setUrl(node.get("url").asText());
                    race.setRaceName(node.get("raceName").asText());

                    JsonNode circuitNode = node.get("Circuit");
                    if (circuitNode != null) {
                        Circut circuit = new Circut();
                        circuit.setCircuitId(circuitNode.get("circuitId").asText());
                        circuit.setUrl(circuitNode.get("url").asText());
                        circuit.setCircuitName(circuitNode.get("circuitName").asText());

                        JsonNode locationNode = circuitNode.get("Location");
                        if (locationNode != null) {
                            Location location = new Location();
                            location.setLat(locationNode.get("lat").asText());
                            location.setLongitude(locationNode.get("long").asText());
                            location.setLocality(locationNode.get("locality").asText());
                            location.setCountry(locationNode.get("country").asText());

                            circuit.setLocation(location);
                        }

                        race.setCircuit(circuit);
                    }

                    races.add(race);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return races;
    }

}
