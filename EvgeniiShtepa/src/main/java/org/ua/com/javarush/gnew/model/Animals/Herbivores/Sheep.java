package org.ua.com.javarush.gnew.model.Animals.Herbivores;

import lombok.Getter;
import org.ua.com.javarush.gnew.Annotations.Animal;
import org.ua.com.javarush.gnew.Annotations.AnimalsUnicode;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;

import java.util.Map;

@Animal
@AnimalsUnicode(value = "\uD83D\uDC11")
public class Sheep extends Herbivores {
    public Sheep() {
        super(140, 3, 70, 15);
    }

    @Override
    public Map<Class<? extends Organism>, Integer> getEatProbability() {
        return Map.of();
    }
}


