package org.ua.com.javarush.gnew.model.Animals.Predator;

import org.ua.com.javarush.gnew.Annotations.Animal;
import org.ua.com.javarush.gnew.Annotations.AnimalsUnicode;
import org.ua.com.javarush.gnew.model.Animals.Herbivores.*;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;

import java.util.HashMap;
import java.util.Map;

@Animal
@AnimalsUnicode(value = "🐻")
public class Bear extends Predator{
    private final Map<Class<? extends Organism>, Integer> EAT_PROBABILITY = new HashMap<>();

    public Bear() {
        super(5, 2, 500, 80);
        EAT_PROBABILITY.put(Cow.class, 70);
        EAT_PROBABILITY.put(Deer.class, 80);
        EAT_PROBABILITY.put(Duck.class, 10);
        EAT_PROBABILITY.put(Goat.class, 70);
        EAT_PROBABILITY.put(Hamster.class, 90);
        EAT_PROBABILITY.put(Horse.class, 40);
        EAT_PROBABILITY.put(Rabbit.class, 80);
        EAT_PROBABILITY.put(Sheep.class, 70);
        EAT_PROBABILITY.put(Snake.class, 80);


    }

    @Override
    public Map<Class<? extends Organism>, Integer> getEatProbability() {
        return EAT_PROBABILITY;
    }
}
