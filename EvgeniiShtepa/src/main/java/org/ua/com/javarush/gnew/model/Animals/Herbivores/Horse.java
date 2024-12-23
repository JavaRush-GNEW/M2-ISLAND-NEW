package org.ua.com.javarush.gnew.model.Animals.Herbivores;

import lombok.Data;
import lombok.Getter;

@Getter
public class Horse extends Herbivores{
    private final int WEIGHT = 400;
    private final int MAX_CELL_COUNT = 20;
    private final int MAX_STEPS_COUNT = 4;
    private final int FOOD_KG_REQUIRED = 60;

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
