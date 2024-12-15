package ua.com.javarush.gnew.Plant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Plant {
    private final String name;
    private final double weight;
    private final int quantity;
    private boolean isEaten;

    public Plant(String name, double weight, int quantity) {
        this.name = name;
        this.weight = weight;
        this.quantity = quantity;
        this.isEaten = false;
    }
}


