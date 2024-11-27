package org.example.model.organism.animal.predator;

import org.example.model.organism.animal.Animal;

public abstract class Predator extends Animal {
    public Predator(double weight, double foodNeeded, int speed, int maxPopulation) {
        super(weight, foodNeeded, speed, maxPopulation);
    }
}
