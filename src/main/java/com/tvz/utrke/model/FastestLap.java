package com.tvz.utrke.model;

import lombok.Data;

@Data
public class FastestLap {
    private String rank;
    private String lap;
    private Time time;
    private AverageSpeed averageSpeed;
}
