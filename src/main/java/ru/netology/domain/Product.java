package ru.netology.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

//@NoArgsConstructor
@Data

public class Product {
    private int id;
    private String name;
    private int cost;

    public Product(int id, String name, int cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

}