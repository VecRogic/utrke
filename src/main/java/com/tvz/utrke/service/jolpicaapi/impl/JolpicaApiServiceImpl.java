package com.tvz.utrke.service.jolpicaapi.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvz.utrke.model.Circut;
import com.tvz.utrke.model.Location;
import com.tvz.utrke.model.Race;
import com.tvz.utrke.model.Season;
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

}
