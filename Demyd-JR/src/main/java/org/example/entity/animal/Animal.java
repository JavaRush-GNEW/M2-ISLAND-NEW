package org.example.entity.animal;

import org.example.entity.LivingEntity;
import org.example.entity.animal.interfaces.Reproduction;


public abstract class Animal extends LivingEntity{
    private final String ANIMAL_NAME;
    private final double MAX_WEIGHT;
    private final int MAX_ANIMAL_CELL;
    private final int MAX_MOVE_SPEED;
    private final double MAX_SATURATION;

    public Animal(String nameAnimal, double maxWeight, int maxAnimalCell, int maxMove, double maxSaturation) {
        this.ANIMAL_NAME = nameAnimal;
        this.MAX_WEIGHT = maxWeight;
        this.MAX_ANIMAL_CELL = maxAnimalCell;
        this.MAX_MOVE_SPEED = maxMove;
        this.MAX_SATURATION = maxSaturation;
    }


    public int getMAX_MOVE_SPEED() {
        return MAX_MOVE_SPEED;
    }
    public String getANIMAL_NAME() {
        return ANIMAL_NAME;
    }

}
