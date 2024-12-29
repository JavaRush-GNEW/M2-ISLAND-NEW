package org.ua.com.javarush.gnew.model.Animals.Herbivores;

import org.ua.com.javarush.gnew.Annotations.Animal;
import org.ua.com.javarush.gnew.Annotations.AnimalsUnicode;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;
import java.util.Map;

@Animal
@AnimalsUnicode(value = "🐎")
public class Horse extends Herbivores {

    public Horse() {
        super(20, 4, 400, 60);
    }

    @Override
    public Map<Class<? extends Organism>, Integer> getEatProbability() {
        return Map.of();
    }
}

