package org.ua.com.javarush.gnew.model.Animals.Predator;

import lombok.Data;
import lombok.Getter;

@Getter
public class Wolf extends Predator {
    private final int MAX_CELL_COUNT = 30;
    private final int MAX_STEPS_COUNT = 3;
    private final int WEIGHT = 50;
    private final int FOOD_KG_REQUIRED = 8;


    @Override
    public void eat() {

    }

    @Override
    public void move() {

    }

    @Override
    public void reproduce() {

    }

    @Override
    public int getMaxCountPerCell() {
        return MAX_CELL_COUNT;
    }
}
