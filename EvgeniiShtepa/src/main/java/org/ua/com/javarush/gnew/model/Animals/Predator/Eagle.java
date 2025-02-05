package org.ua.com.javarush.gnew.model.Animals.Predator;

import org.ua.com.javarush.gnew.Annotations.Animal;
import org.ua.com.javarush.gnew.Annotations.AnimalsUnicode;
import org.ua.com.javarush.gnew.model.Animals.Herbivores.*;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;

import java.util.HashMap;
import java.util.Map;


@Animal
@AnimalsUnicode(value = "\uD83E\uDD85")
public class Eagle extends Predator {
    private final Map<Class<? extends Organism>, Integer> EAT_PROBABILITY = new HashMap<>();

    public Eagle() {
        super(20, 3, 6, 4);
        EAT_PROBABILITY.put(Cow.class, 0);
        EAT_PROBABILITY.put(Deer.class, 0);
        EAT_PROBABILITY.put(Duck.class, 80);
        EAT_PROBABILITY.put(Goat.class, 0);
        EAT_PROBABILITY.put(Hamster.class, 90);
        EAT_PROBABILITY.put(Horse.class, 0);
        EAT_PROBABILITY.put(Rabbit.class, 90);
        EAT_PROBABILITY.put(Sheep.class, 0);
        EAT_PROBABILITY.put(Fox.class, 10);

    }

    @Override
    public Map<Class<? extends Organism>, Integer> getEatProbability() {
        return EAT_PROBABILITY;
    }
}

