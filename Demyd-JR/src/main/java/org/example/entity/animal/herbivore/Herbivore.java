package org.example.entity.animal.herbivore;

import org.example.entity.animal.Animal;

public abstract class Herbivore extends Animal {

    public Herbivore(String nameAnimal, int maxWeight, int maxAnimalCell, int maxMove, int maxSaturation) {
        super(nameAnimal, maxWeight, maxAnimalCell, maxMove, maxSaturation);
    }
}
