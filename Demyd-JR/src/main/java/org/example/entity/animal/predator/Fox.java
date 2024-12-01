package org.example.entity.animal.predator;

import org.example.entity.animal.Animal;
import org.example.entity.animal.herbivore.*;

import java.util.Map;

public class Fox extends Predator{
    private static final Map<Class<? extends Animal>, Integer> HUNTING_CHANCES = Map.of(
            Rabbit.class, 70,
            Duck.class,60,
            Mouse.class,90,
            Geese.class,40
    );
    public Fox() {
        super("Fox",8,30,2,2);
    }

    @Override
    public Map<Class<? extends Animal>, Integer> getHuntingChances() {
        return HUNTING_CHANCES;
    }
}
