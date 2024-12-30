package org.ua.com.javarush.gnew.model.Animals.Predator;

import org.ua.com.javarush.gnew.Annotations.Animal;
import org.ua.com.javarush.gnew.Annotations.AnimalsUnicode;
import org.ua.com.javarush.gnew.model.Animals.Herbivores.*;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;
import java.util.HashMap;
import java.util.Map;

@Animal
@AnimalsUnicode(value = "🐺")
public class Wolf extends Predator {

    private final Map<Class<? extends Organism>, Integer> EAT_PROBABILITY = new HashMap<>();

    public Wolf() {
        super(30, 3, 50, 8);
        EAT_PROBABILITY.put(Cow.class, 15);
        EAT_PROBABILITY.put(Deer.class, 15);
        EAT_PROBABILITY.put(Duck.class, 40);
        EAT_PROBABILITY.put(Goat.class, 60);
        EAT_PROBABILITY.put(Hamster.class, 80);
        EAT_PROBABILITY.put(Horse.class, 10);
        EAT_PROBABILITY.put(Rabbit.class, 60);
        EAT_PROBABILITY.put(Sheep.class, 70);
    }

    @Override
    public Map<Class<? extends Organism>, Integer> getEatProbability() {
        return EAT_PROBABILITY;
    }
}
