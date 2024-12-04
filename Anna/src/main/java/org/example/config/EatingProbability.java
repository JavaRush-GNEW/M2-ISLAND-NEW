package org.example.config;

import org.example.model.organism.Organism;
import org.example.model.organism.animal.Animal;

import java.util.Map;

public class EatingProbability<T extends Animal<?>> {
    private final Map<Class<? extends Organism>, Integer> probabilities;

    public EatingProbability(Map<Class<? extends Organism>, Integer> probabilities) {
        this.probabilities = probabilities;
    }

    public Integer getProbability(Class<? extends Organism> preyClass) {
        return probabilities.getOrDefault(preyClass, 0);
    }
}
