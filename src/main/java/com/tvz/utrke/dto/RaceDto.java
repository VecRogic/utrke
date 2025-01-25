package com.tvz.utrke.dto;

import groovy.transform.Sortable;
import lombok.Data;

@Sortable
@Data
public class RaceDto {
    private String season;
    private String round;
    private String url;
    private String raceName;
    private CircutDto circuit;
    private String date;
}
