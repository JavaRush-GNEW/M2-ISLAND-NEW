package org.example.entity.animal.herbivore;

import org.example.entity.animal.Animal;

public class Geese extends Herbivore{
    public Geese(){
        super("Geese",0.01,1000,0,0);
    }
    @Override
    public boolean canReproduce() {
        return getSaturation() > (MAX_SATURATION / 2);
    }

    @Override
    public Animal createNewAnimal() {
        return new Geese();
    }
}
