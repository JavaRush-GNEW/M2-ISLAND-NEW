package ua.com.javarush.gnew.Animal;

import lombok.Data;
import lombok.Getter;
import ua.com.javarush.gnew.Field.Cell;
import ua.com.javarush.gnew.Field.Field;
import ua.com.javarush.gnew.Diet.Herbivores;

@Data
@Getter


public abstract class Animal implements Herbivores {
    private String name;
    private double weight;
    private int quantity;
    private double foodNeeded;
    private double saturation;
    private int satiation;
    private boolean alive;

    public Animal(String name, double weight, int quantity, double foodNeeded) {
        this.name = name;
        this.weight = weight;
        this.quantity = quantity;
        this.foodNeeded = foodNeeded;
        this.saturation = 0;
        this.alive = true;
    }

     public void increaseSaturation(double food) {
        saturation += food;
        if (saturation > foodNeeded) {
            saturation =  foodNeeded;
        }
    }

    public void decreaseSaturation(double food) {
        saturation -= food;
        if (saturation < 0) {
            saturation = 0;
        }
    }

    public abstract void move(Cell[][] cells, int x, int y);

    public abstract void eat(Cell[][] cells, int x, int y);

    public abstract void reproduce(Field field);

    public abstract void death();
}


