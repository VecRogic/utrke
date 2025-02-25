package com.tvz.utrke.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Race {
    private String season;
    private String round;
    private String url;
    private String raceName;

    @JsonProperty("Circuit")
    private Circut circuit;
    private String date;
}
