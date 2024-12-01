package org.example.entity.animal.predator;

import org.example.entity.animal.Animal;
import org.example.entity.animal.herbivore.*;

import java.util.Map;

public class Bear extends Predator{
    private static final Map<Class<? extends Animal>, Integer> HUNTING_CHANCES = Map.of(
            BoaConstrictor.class,80,
            Horse.class,40,
            Deer.class,80,
            Rabbit.class,80,
            Mouse.class,90,
            Goat.class,70,
            Sheep.class,70,
            Boar.class,50,
            Buffalo.class,20,
            Duck.class,10
    );
    public Bear(){
        super("Bear",500,5,2,80);
    }

    @Override
    public Map<Class<? extends Animal>, Integer> getHuntingChances() {
        return HUNTING_CHANCES;
    }
}
