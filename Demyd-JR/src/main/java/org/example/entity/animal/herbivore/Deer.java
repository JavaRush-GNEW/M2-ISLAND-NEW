package org.example.entity.animal.herbivore;

import org.example.entity.animal.Animal;

public class Deer extends Herbivore{
    public Deer() {
        super("Deer",300,20,4,50);
    }
    @Override
    public boolean canReproduce() {
        return getSaturation() > (MAX_SATURATION / 2);
    }

    @Override
    public Animal createNewAnimal() {
        return new Deer();
    }
}
