package org.ua.com.javarush.gnew.model.Animals.Herbivores;

import org.ua.com.javarush.gnew.Annotations.Animal;
import org.ua.com.javarush.gnew.Annotations.AnimalsUnicode;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;

import java.util.Map;

@Animal
@AnimalsUnicode(value = "\uD83E\uDD86")
public class Duck extends Herbivores{
    public Duck() {
        super(200, 4, 1, 4);
    }

    @Override
    public Map<Class<? extends Organism>, Integer> getEatProbability() {
        return Map.of();
    }
}

