package org.example.entity.animal.predator;

import org.example.entity.animal.Animal;

public abstract class Predator extends Animal {
    private String name;
    public Predator(String animalName) {
        this.name = animalName;
    }

    public abstract void hunt();

    @Override
    public String toString() {
        return name;
    }
}
