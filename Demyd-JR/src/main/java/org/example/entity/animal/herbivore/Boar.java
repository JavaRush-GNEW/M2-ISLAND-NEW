package org.example.entity.animal.herbivore;

import org.example.entity.animal.Animal;
import org.example.entity.animal.interfaces.Hunting;

import java.util.Map;

public class Boar extends Herbivore implements Hunting {
    private static final Map<Class<? extends Animal>, Integer> HUNTING_CHANCES = Map.of(
            Mouse.class,50,
            Geese.class,90
    );
    public Boar(){
        super("Boar",400,50,2,50);
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
        return new Boar();
    }
}
