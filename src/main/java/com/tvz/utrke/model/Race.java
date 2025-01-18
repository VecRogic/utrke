package com.tvz.utrke.model;

import lombok.Data;

@Data
public class Race {
    private String season;
    private String round;
    private String url;
    private String raceName;
    private Circut circuit;
    private String date;
}
