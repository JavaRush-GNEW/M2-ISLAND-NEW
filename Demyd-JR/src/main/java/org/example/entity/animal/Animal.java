package org.example.entity.animal;

import org.example.entity.LivingEntity;

public abstract class Animal extends LivingEntity {
    private final int maxMoveRadius;
    private final String nameAnimal;

    public Animal(String nameAnimal, int maxMove) {
        this.nameAnimal = nameAnimal;
        this.maxMoveRadius = maxMove;
    }


    public int getMaxMoveRadius() {
        return maxMoveRadius;
    }
    public String getNameAnimal() {
        return nameAnimal;
    }
}
