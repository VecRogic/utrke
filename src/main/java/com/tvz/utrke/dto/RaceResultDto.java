package com.tvz.utrke.dto;

import lombok.Data;

@Data
public class RaceResultDto {
    String number;
    Integer position;
    Long points;
    DriverDto driver;
    ConstructorDto constructor;
    TimeDto time;
    AverageSpeedDto averageSpeed;
}
