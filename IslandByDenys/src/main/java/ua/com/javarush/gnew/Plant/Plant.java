package ua.com.javarush.gnew.Plant;

import lombok.Getter;

@Getter
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

    public boolean isEaten() {
        return isEaten;
    }

    public void setEaten(boolean isEaten) {
        this.isEaten = isEaten;
    }

    public int getWeight() {
        return (int) weight;
    }
}


