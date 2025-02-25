package com.tvz.utrke.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RaceResult {
    private String number;
    private Integer position;
    private String positionText;
    private String points;

    @JsonProperty("Driver")
    private Driver driver;

    @JsonProperty("Constructor")
    private Constructor constructor;
    private String grid;
    private String laps;
    private String status;

    @JsonProperty("Time")
    private Time time;

    @JsonProperty("FastestLap")
    private FastestLap fastestLap;
}