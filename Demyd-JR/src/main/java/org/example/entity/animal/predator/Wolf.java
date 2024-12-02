package org.example.entity.animal.predator;

import org.example.entity.animal.Animal;
import org.example.entity.animal.herbivore.*;

import java.util.Map;


public class Wolf extends Predator{
    private static final Map<Class<? extends Animal>, Integer> HUNTING_CHANCES = Map.of(
            Horse.class, 10,
            Rabbit.class, 60,
            Boar.class,15,
            Buffalo.class,10,
            Deer.class,15,
            Duck.class,40,
            Goat.class,60,
            Mouse.class,80,
            Sheep.class,70
    );
    public Wolf() {
        super("Wolf", 50,30,3,8);
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
        return new Wolf();
    }
}
