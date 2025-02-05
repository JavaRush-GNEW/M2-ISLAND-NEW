package org.example.entity.animal.herbivore;

import org.example.entity.animal.Animal;

public class Buffalo extends Herbivore{
    public Buffalo(){
        super("Buffalo",700,10,3,100);
    }
    @Override
    public boolean canReproduce() {
        return getSaturation() > (MAX_SATURATION / 2);
    }

    @Override
    public Animal createNewAnimal() {
        return new Buffalo();
    }
}
