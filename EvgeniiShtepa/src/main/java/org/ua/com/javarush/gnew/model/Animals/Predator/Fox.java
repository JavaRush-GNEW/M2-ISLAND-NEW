package org.ua.com.javarush.gnew.model.Animals.Predator;

import org.ua.com.javarush.gnew.Annotations.Animal;
import org.ua.com.javarush.gnew.Annotations.AnimalsUnicode;
import org.ua.com.javarush.gnew.model.Animals.Herbivores.*;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;

import java.util.HashMap;
import java.util.Map;


@Animal
@AnimalsUnicode(value = "\uD83E\uDD8A")
public class Fox extends Predator{
    private final Map<Class<? extends Organism>, Integer> EAT_PROBABILITY = new HashMap<>();

    public Fox() {
        super(30, 2, 8, 2);
        EAT_PROBABILITY.put(Cow.class, 0);
        EAT_PROBABILITY.put(Deer.class, 0);
        EAT_PROBABILITY.put(Duck.class, 60);
        EAT_PROBABILITY.put(Goat.class, 0);
        EAT_PROBABILITY.put(Hamster.class, 90);
        EAT_PROBABILITY.put(Horse.class, 0);
        EAT_PROBABILITY.put(Rabbit.class, 70);
        EAT_PROBABILITY.put(Sheep.class, 0);
    }

    @Override
    public Map<Class<? extends Organism>, Integer> getEatProbability() {
        return EAT_PROBABILITY;
    }
}


