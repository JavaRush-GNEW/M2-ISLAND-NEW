package org.example.entity.animal.predator;

import org.example.entity.animal.Animal;
import org.example.entity.animal.herbivore.*;

import java.util.Map;

public class BoaConstrictor extends Predator{
    private static final Map<Class<? extends Animal>, Integer> HUNTING_CHANCES = Map.of(
            Fox.class,15,
            Rabbit.class,20,
            Mouse.class,40,
            Duck.class,10
    );
    public BoaConstrictor() {
        super("BoaConstrictor",15,30,1,3);
    }

    @Override
    public Map<Class<? extends Animal>, Integer> getHuntingChances() {
        return HUNTING_CHANCES;
    }
    @Override
    public boolean canReproduce() {
        return getSaturation() > (MAX_SATURATION / 2);
    }

    @Override
    public Animal createNewAnimal() {
        return new BoaConstrictor();
    }
}
