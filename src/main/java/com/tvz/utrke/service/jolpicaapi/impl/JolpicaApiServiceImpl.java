package com.tvz.utrke.service.jolpicaapi.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvz.utrke.model.*;
import com.tvz.utrke.service.jolpicaapi.JolpicaApiService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@lombok.extern.slf4j.Slf4j
@Slf4j
@Service
public class JolpicaApiServiceImpl implements JolpicaApiService {

    private final WebClient webClient;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${app.jolpica.url}")
    private String jolpicaUrl;

    public JolpicaApiServiceImpl(WebClient.Builder webClientBuilder,
                                 @Value("${app.jolpica.url}") String jolpicaUrl) { // ✅ Inject URL in constructor
        this.webClient = webClientBuilder
                .baseUrl(jolpicaUrl)
                .defaultHeader(HttpHeaders.ACCEPT, "application/json")
                .build();
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
        log.info("Initialized with baseUrl: " + jolpicaUrl);
    }

    @Override
    public Mono<List<Season>> fetchSeasons() {
        return webClient.get()
                .uri("/seasons/?limit=76")
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(jsonResponse -> {
                    JsonNode seasonsNode = jsonResponse.path("MRData").path("SeasonTable").path("Seasons");

                    return objectMapper.convertValue(seasonsNode, objectMapper.getTypeFactory().constructCollectionType(List.class, Season.class));
                });
    }

    @Override
    public Mono<List<Race>> fetchRacesBySeason(String seasonYear) {
        return webClient.get()
                .uri(seasonYear + "/races/")
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(jsonResponse -> {
                    JsonNode seasonsNode = jsonResponse.path("MRData").path("RaceTable").path("Races");

                    return objectMapper.convertValue(seasonsNode, objectMapper.getTypeFactory().constructCollectionType(List.class, Race.class));
                });
    }

    @Override
    public Mono<List<RaceResult>> fetchRaceResults(String seasonYear, int round) {
        return webClient.get()
                .uri(seasonYear + "/" + round + "/results/")
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(jsonResponse -> {
                    JsonNode seasonsNode = jsonResponse.path("MRData").path("RaceTable").path("Races").get(0).path("Results");

                    return objectMapper.convertValue(seasonsNode, objectMapper.getTypeFactory().constructCollectionType(List.class, RaceResult.class));
                });
    }

    @Override
    public Mono<Driver> getDriverById(String driverId) {
        String url = "drivers/" + driverId;
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(jsonResponse -> {
                    JsonNode driverNode = jsonResponse.path("MRData").path("DriverTable").path("Drivers");

                    return objectMapper.convertValue(driverNode, objectMapper.getTypeFactory().constructCollectionType(List.class, Driver.class));
                });
    }

    @Override
    public Mono<List<Race>> getRacesByDriverId(String driverId) {
        String url = "drivers/" + driverId + "/races/";
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(jsonResponse -> {
                    JsonNode driverNode = jsonResponse.path("MRData").path("RaceTable").path("Races");

                    return objectMapper.convertValue(driverNode, objectMapper.getTypeFactory().constructCollectionType(List.class, Race.class));
                });
    }

    @Override
    public Mono<List<Driver>> getDriversByConstructorId(String constructorId) {
        String url = "constructors/" + constructorId + "/drivers";
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(jsonResponse -> {
                    JsonNode driversNode = jsonResponse.path("MRData").path("DriverTable").path("Drivers");

                    return objectMapper.convertValue(driversNode, objectMapper.getTypeFactory().constructCollectionType(List.class, Driver.class));
                });
    }

    @Override
    public Constructor getConstructorById(String constructorId) {
        String url = jolpicaUrl + "/constructors/" + constructorId;
        String response = restTemplate.getForObject(url, String.class);

        Constructor constructor = null;

        try {
            // Initialize ObjectMapper for parsing JSON
            ObjectMapper objectMapper = new ObjectMapper();
            // Parse the response into a JsonNode
            JsonNode root = objectMapper.readTree(response);
            JsonNode constructorsNode = root.path("MRData").path("ConstructorTable").path("Constructors");

            // Check if there is a constructor in the response
            if (constructorsNode.isArray() && constructorsNode.size() > 0) {
                JsonNode constructorNode = constructorsNode.get(0); // Since we expect one constructor object

                constructor = new Constructor();
                constructor.setConstructorId(constructorNode.get("constructorId").asText());
                constructor.setUrl(constructorNode.get("url").asText());
                constructor.setName(constructorNode.get("name").asText());
                constructor.setNationality(constructorNode.get("nationality").asText());
            }
        } catch (Exception e) {
            // Log the exception if any occurs
            e.printStackTrace();
        }

        return constructor;
    }

}
