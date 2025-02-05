package org.ua.com.javarush.gnew.model.Animals.Herbivores;

import lombok.Getter;
import org.ua.com.javarush.gnew.Annotations.Animal;
import org.ua.com.javarush.gnew.Annotations.AnimalsUnicode;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;

import java.util.Map;

@Animal
@AnimalsUnicode(value = "\uD83D\uDC07")
public class Rabbit extends Herbivores {
    public Rabbit() {
        super(150, 2, 2, 4);
    }

    @Override
    public Map<Class<? extends Organism>, Integer> getEatProbability() {
        return Map.of();
    }
}

