package com.tvz.utrke.dto;

import lombok.Data;

@Data
public class SeasonDto {
    String seasonYear;

    public String getSeasonYear() {
        return seasonYear;
    }

    public void setSeasonYear(String seasonYear) {
        this.seasonYear = seasonYear;
    }
}
