package com.tvz.utrke.model;

import lombok.Data;

@Data
public class Location {
    private String lat;
    private String longitude;
    private String locality;
    private String country;
}
