package org.example.model.map;


import org.example.model.organism.Organism;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cell {
    private final int x;
    private final int y;
    private Map<Class<? extends Organism>, Set<Organism>> residents;

    public Cell(int x, int y, Map<Class<? extends Organism>, Set<Organism>> residents) {
        this.x = x;
        this.y = y;
        this.residents = residents;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Map<Class<? extends Organism>, Set<Organism>> getResidents() {
        return residents;
    }

    public void setResidents(Map<Class<? extends Organism>, Set<Organism>> residents) {
        this.residents = residents;
    }

    public boolean add(Organism organism) {
        Class<? extends Organism> organismClass = organism.getClass();
        residents.putIfAbsent(organismClass, new HashSet<>());
        return residents.get(organismClass).add(organism);
    }

    public boolean remove(Organism organism) {
        Class<? extends Organism> organismClass = organism.getClass();
        Set<Organism> organisms = residents.get(organismClass);
        return organisms != null && organisms.remove(organism);
    }

    public boolean canAccommodate(Organism organism) {
        Class<? extends Organism> organismClass = organism.getClass();
        Set<Organism> sameSpecies = residents.getOrDefault(organismClass, new HashSet<>());
        int maxPopulation = organism.getMaxPopulation();
        if (sameSpecies.size() >= maxPopulation) {
            return false;
        }
        return true;
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
