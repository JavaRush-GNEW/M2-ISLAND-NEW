package org.example.entity.animal.herbivore;

import org.example.entity.animal.Animal;
import org.example.entity.animal.predator.Wolf;

public class Sheep extends Herbivore{
    public Sheep(){
        super("Sheep",60,140,3,10);
    }
    @Override
    public boolean canReproduce() {
        return getSaturation() > (MAX_SATURATION / 2);
    }

    @Override
    public Animal createNewAnimal() {
        return new Sheep();
    }
}
