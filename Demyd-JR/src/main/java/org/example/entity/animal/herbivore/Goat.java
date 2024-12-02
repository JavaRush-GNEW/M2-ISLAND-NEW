package org.example.entity.animal.herbivore;

import org.example.entity.animal.Animal;

public class Goat extends Herbivore{
    public Goat(){
        super("Goat",60,140,3,15);
    }
    @Override
    public boolean canReproduce() {
        return getSaturation() > (MAX_SATURATION / 2);
    }

    @Override
    public Animal createNewAnimal() {
        return new Goat();
    }
}
