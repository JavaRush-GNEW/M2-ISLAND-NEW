package org.ua.com.javarush.gnew.model.Animals.Herbivores;

import org.ua.com.javarush.gnew.Annotations.Animal;
import org.ua.com.javarush.gnew.Annotations.AnimalsUnicode;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;

import java.util.Map;

@Animal
@AnimalsUnicode(value = "\uD83D\uDC04")
public class Cow extends Herbivores {
    public Cow() {
        super(50, 2, 400, 50);
    }

    @Override
    public Map<Class<? extends Organism>, Integer> getEatProbability() {
        return Map.of();
    }
}


