package org.example.entity.animal.herbivore;

import org.example.entity.animal.Animal;

public class Horse extends Herbivore{
    public Horse(){
        super("Horse",400,20,4,60);
    }
    @Override
    public boolean canReproduce() {
        return getSaturation() > (MAX_SATURATION / 2);
    }

    @Override
    public Animal createNewAnimal() {
        return new Horse();
    }

}
