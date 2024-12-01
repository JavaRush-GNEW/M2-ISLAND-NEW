package org.example.entity.animal.predator;

import org.example.entity.animal.Animal;
import org.example.entity.animal.interfaces.Hunting;

public abstract class Predator extends Animal implements Hunting{

    public Predator(String nameAnimal, double maxWeight, int maxAnimalCell, int maxMove, int maxSaturation) {
        super(nameAnimal, maxWeight, maxAnimalCell, maxMove, maxSaturation);
    }
}
