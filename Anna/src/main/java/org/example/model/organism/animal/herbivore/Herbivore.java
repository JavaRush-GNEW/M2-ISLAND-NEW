package org.example.model.organism.animal.herbivore;

import org.example.model.organism.animal.Animal;
import org.example.model.organism.plant.Plant;

public abstract class Herbivore extends Animal {

    public Herbivore(double weight, double foodNeeded, int speed, int maxPopulation) {
        super(weight, foodNeeded, speed, maxPopulation);
        eatingProbabilities.put(Plant.class, 100);
    }
}
