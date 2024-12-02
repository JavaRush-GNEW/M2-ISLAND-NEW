package org.example.entity.animal.predator;

import org.example.entity.animal.Animal;
import org.example.entity.animal.herbivore.Duck;
import org.example.entity.animal.herbivore.Mouse;
import org.example.entity.animal.herbivore.Rabbit;

import java.util.Map;

public class Eagle extends Predator{
    private static final Map<Class<? extends Animal>, Integer> HUNTING_CHANCES = Map.of(
            Rabbit.class, 90,
            Duck.class,80,
            Mouse.class,90,
            Fox.class,10
    );
    public Eagle() {
        super("Eagle",6,20,3,1);
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
        return new Eagle();
    }
}
