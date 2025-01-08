package org.example.entity.animal.herbivore;

import org.example.entity.animal.Animal;
import org.example.entity.animal.interfaces.Hunting;

import java.util.Map;

public class Duck extends Herbivore implements Hunting {
    private static final Map<Class<? extends Animal>, Integer> HUNTING_CHANCES = Map.of(
            Geese.class,90
    );
    public Duck() {
        super("Duck",1,200,4,0.15);
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
        return new Duck();
    }
}
