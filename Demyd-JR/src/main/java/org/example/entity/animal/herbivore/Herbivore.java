package org.example.entity.animal.herbivore;

import org.example.entity.animal.Animal;
import org.example.entity.animal.interfaces.EatingPlant;

public abstract class Herbivore extends Animal implements EatingPlant {

    public Herbivore(String nameAnimal, double maxWeight, int maxAnimalCell, int maxMove, double maxSaturation) {
        super(nameAnimal, maxWeight, maxAnimalCell, maxMove, maxSaturation);
    }
}
