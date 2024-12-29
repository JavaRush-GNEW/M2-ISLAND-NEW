package org.ua.com.javarush.gnew.model.Animals.Predator;



import org.ua.com.javarush.gnew.Annotations.AnimalsUnicode;
import org.ua.com.javarush.gnew.model.Animals.Herbivores.Horse;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;

import java.util.HashMap;
import java.util.Map;

@AnimalsUnicode(value = "🐺")
public class Wolf extends Predator {

    private final Map<Class<? extends Organism>, Integer> EAT_PROBABILITY = new HashMap<>();

    public Wolf() {
        super(2, 1, 50, 8);
        EAT_PROBABILITY.put(Horse.class, 10);
    }

    @Override
    public Map<Class<? extends Organism>, Integer> getEatProbability() {
        return EAT_PROBABILITY;
    }
}
