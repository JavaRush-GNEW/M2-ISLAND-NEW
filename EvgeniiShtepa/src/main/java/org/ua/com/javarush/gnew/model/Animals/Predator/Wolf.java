package org.ua.com.javarush.gnew.model.Animals.Predator;


import lombok.Getter;
import org.ua.com.javarush.gnew.model.Animals.Herbivores.Horse;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;

import java.util.HashMap;
import java.util.Map;


public class Wolf extends Predator {
    @Getter
    private static final Map<Class<? extends Organism>, Integer> EAT_PROBABILITY = new HashMap<>();

    static {
        EAT_PROBABILITY.put(Horse.class, 10);
    }

    public Wolf() {
        super(2, 3, 50, 8);
    }


}
