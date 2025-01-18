package com.tvz.utrke.service.jolpicaapi.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
            // Parse JSON and extract Seasons array
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

}
