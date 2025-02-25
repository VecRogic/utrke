package com.tvz.utrke.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Circut {
    private String circuitId;
    private String url;
    private String circuitName;

    @JsonProperty("Location")
    private Location location;
}
