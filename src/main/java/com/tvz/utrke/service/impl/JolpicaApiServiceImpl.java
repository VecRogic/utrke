package com.tvz.utrke.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvz.utrke.model.*;
import com.tvz.utrke.service.JolpicaApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

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
    public Mono<Constructor> getConstructorById(String constructorId) {
        String url = jolpicaUrl + "/constructors/" + constructorId;

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(jsonResponse -> {
                    JsonNode constructorNode = jsonResponse.path("MRData").path("ConstructorTable").path("Constructors");

                    return objectMapper.convertValue(constructorNode, objectMapper.getTypeFactory().constructCollectionType(List.class, Constructor.class));
                });
    }

}
