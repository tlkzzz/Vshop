package com.Vshop.seller.test;

import lombok.Data;

import java.util.List;

@Data
public class Province{
    private int id;
    private String name;
    private List<City> cities;
}