package org.example.entity.animal.herbivore;

import org.example.entity.animal.Animal;

public class Rabbit extends Herbivore{
    public Rabbit(){
        super("Rabbit", 2,150,2,0.45);
    }
    @Override
    public boolean canReproduce() {
        return getSaturation() > (MAX_SATURATION / 2);
    }

    @Override
    public Animal createNewAnimal() {
        return new Rabbit();
    }
}
