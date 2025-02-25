package com.tvz.utrke.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FastestLap {
    private String rank;
    private String lap;

    @JsonProperty("Time")
    private Time time;

    @JsonProperty("AverageSpeed")
    private AverageSpeed averageSpeed;
}
