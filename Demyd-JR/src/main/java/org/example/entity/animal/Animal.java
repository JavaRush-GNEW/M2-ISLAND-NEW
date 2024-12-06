package org.example.entity.animal;

import org.example.entity.LivingEntity;
import org.example.entity.animal.interfaces.Hunting;
import org.example.entity.animal.interfaces.Reproduction;

import java.util.Random;

public abstract class Animal extends LivingEntity implements Reproduction {
    protected final String ANIMAL_NAME;
    protected final double MAX_WEIGHT;
    protected final int MAX_ANIMAL_CELL;
    protected final int MAX_MOVE_SPEED;
    protected final double MAX_SATURATION;

    private double saturation; // Поточна насиченість

    private static final Random RANDOM = new Random();

    public Animal(String nameAnimal, double maxWeight, int maxAnimalCell, int maxMove, double maxSaturation) {
        this.ANIMAL_NAME = nameAnimal;
        this.MAX_WEIGHT = maxWeight;
        this.MAX_ANIMAL_CELL = maxAnimalCell;
        this.MAX_MOVE_SPEED = maxMove;
        this.MAX_SATURATION = maxSaturation;

        // Встановлюємо випадкові значення для ваги та насиченості
        this.saturation = RANDOM.nextDouble() * MAX_SATURATION; // Насиченість від 0 до MAX_SATURATION
    }

    public String getNameAnimal() {
        return ANIMAL_NAME;
    }

    public double getSaturation() {
        return saturation;
    }

    public double getMaxSaturation() {
        return MAX_SATURATION;
    }

    public int getMaxPerCellAnimal() {
        return MAX_ANIMAL_CELL;
    }

    public int getMaxStep() {
        return MAX_MOVE_SPEED;
    }

    public void increaseSaturation(double amount) {
        saturation = Math.min(MAX_SATURATION, saturation + amount); // Насиченість не більше MAX_SATURATION
    }

    public void decreaseSaturation(double amount) {
        saturation = Math.max(0.0, saturation - amount); // Насиченість не може бути нижче 0
    }



}
