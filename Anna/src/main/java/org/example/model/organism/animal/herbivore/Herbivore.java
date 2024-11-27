package org.example.model.organism.animal.herbivore;

import org.example.model.organism.animal.Animal;

public abstract class Herbivore extends Animal {

    public Herbivore(double weight, double foodNeeded, int speed, int maxPopulation) {
        super(weight, foodNeeded, speed, maxPopulation);
    }
}
