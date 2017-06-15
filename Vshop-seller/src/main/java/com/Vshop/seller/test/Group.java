package com.Vshop.seller.test;

import lombok.Data;

import java.util.List;

@Data
public class Group{
    private int id;
    private String name;
    private List<Province> provinces;
}