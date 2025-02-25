package com.tvz.utrke.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Location {
    private String lat;

    @JsonProperty("long")
    private String longitude;
    private String locality;
    private String country;
}
