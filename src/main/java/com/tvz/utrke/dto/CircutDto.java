package com.tvz.utrke.dto;

import lombok.Data;

@Data
public class CircutDto {
    private String circuitId;
    private String url;
    private String circuitName;
    private LocationDto location;
}
