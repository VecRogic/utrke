package com.tvz.utrke.dto;

import groovy.transform.Sortable;
import lombok.Data;

@Sortable
@Data
public class RacePredictionDto {
    String driver;
    String constructor;
    String explanation;
}
