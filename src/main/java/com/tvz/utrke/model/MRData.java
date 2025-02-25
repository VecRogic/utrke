package com.tvz.utrke.model;

import lombok.Data;

import java.util.List;

@Data
public class MRData<T> {
    private String series;
    private String url;
    private int limit;
    private int offset;
    private int total;
    private List<Season> SeasonTable;
}