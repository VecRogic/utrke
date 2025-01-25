package com.tvz.utrke.dto;

import lombok.Data;

@Data
public class RaceDto {
    private String season;
    private String round;
    private String url;
    private String raceName;
    private CircutDto circuit;
    private String date;
}
