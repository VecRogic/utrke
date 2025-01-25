package com.tvz.utrke.model;

import lombok.Data;

@Data
public class RaceResult {
    private String number;
    private Integer position;
    private String positionText;
    private String points;
    private Driver driver;
    private Constructor constructor;
    private String grid;
    private String laps;
    private String status;
    private Time time;
    private FastestLap fastestLap;
}