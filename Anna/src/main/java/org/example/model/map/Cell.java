package org.example.model.map;


import org.example.model.organism.Organism;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cell {
    private final Map<Class<? extends Organism>, Set<Organism>> residents;

    public Cell(Map<Class<? extends Organism>, Set<Organism>> residents) {
        this.residents = residents;
    }

    public Map<Class<? extends Organism>, Set<Organism>> getResidents() {
        return residents;
    }

    public boolean add(Organism organism) {
        Class<? extends Organism> organismClass = organism.getClass();
        residents.putIfAbsent(organismClass, new HashSet<>());
        return residents.get(organismClass).add(organism);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Cell residents:\n");
        residents.forEach((key, value) -> sb.append(key.getSimpleName())
                .append(": ")
                .append(value.size())
                .append("\n")
        );
        return sb.toString();
    }
}
