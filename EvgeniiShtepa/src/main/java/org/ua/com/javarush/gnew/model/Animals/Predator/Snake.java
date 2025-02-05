package org.ua.com.javarush.gnew.model.Animals.Predator;

import org.ua.com.javarush.gnew.Annotations.Animal;
import org.ua.com.javarush.gnew.Annotations.AnimalsUnicode;
import org.ua.com.javarush.gnew.model.Animals.Herbivores.*;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;

import java.util.HashMap;
import java.util.Map;


@Animal
@AnimalsUnicode(value = "\uD83D\uDC0D")
public class Snake extends Predator {
    private final Map<Class<? extends Organism>, Integer> EAT_PROBABILITY = new HashMap<>();

    public Snake() {
        super(30, 1, 15, 3);
        EAT_PROBABILITY.put(Cow.class, 0);
        EAT_PROBABILITY.put(Deer.class, 0);
        EAT_PROBABILITY.put(Duck.class, 10);
        EAT_PROBABILITY.put(Goat.class, 0);
        EAT_PROBABILITY.put(Hamster.class, 40);
        EAT_PROBABILITY.put(Horse.class, 0);
        EAT_PROBABILITY.put(Rabbit.class, 20);
        EAT_PROBABILITY.put(Sheep.class, 0);
        EAT_PROBABILITY.put(Fox.class, 15);
    }

    @Override
    public Map<Class<? extends Organism>, Integer> getEatProbability() {
        return EAT_PROBABILITY;
    }
}

