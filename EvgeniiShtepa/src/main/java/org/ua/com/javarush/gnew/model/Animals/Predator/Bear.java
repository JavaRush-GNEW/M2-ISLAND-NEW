package org.ua.com.javarush.gnew.model.Animals.Predator;

import org.ua.com.javarush.gnew.model.Animals.Herbivores.Horse;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;

import java.util.HashMap;
import java.util.Map;

public class Bear extends Predator{
    private final Map<Class<? extends Organism>, Integer> EAT_PROBABILITY = new HashMap<>();

    public Bear() {
        super(5, 2, 500, 80);
        EAT_PROBABILITY.put(Horse.class, 0);
    }

    @Override
    public Map<Class<? extends Organism>, Integer> getEatProbability() {
        return EAT_PROBABILITY;
    }
}
