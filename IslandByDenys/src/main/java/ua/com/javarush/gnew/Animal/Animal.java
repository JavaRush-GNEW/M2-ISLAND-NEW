package ua.com.javarush.gnew.Animal;

import lombok.Data;
import lombok.Getter;
import ua.com.javarush.gnew.Field.Cell;
import ua.com.javarush.gnew.Field.Field;
import ua.com.javarush.gnew.Plant.Plant;

@Data
@Getter


public abstract class Animal {
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

    public int getWeight() {
        return (int) weight;
    }

    protected void log(String message) {
        System.out.println("[Animal] " + message);
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
    public boolean eatPlant(Plant plant) {
        if (isHerbivore()) {
            this.satiation += plant.getWeight();
            return true;
        }
        return false;
    }

    public abstract boolean isHerbivore();

    public abstract void move(Cell[][] cells, int x, int y);

    public abstract void eat(Cell[][] cells, int x, int y);

    public abstract void reproduce(Field field);

    public abstract void death();
}


