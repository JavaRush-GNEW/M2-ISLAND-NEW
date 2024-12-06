package GameMap;

import Organism.*;
import Organism.Animal.Animal;

import java.util.*;

public class Cell {
    private int row;
    private int col;
    private double plantMass;
    private Map<String, List<Organism>> organisms = new HashMap<>();

    public Cell(int row, int col, double plantMass) {
        this.row = row;
        this.col = col;
        this.plantMass = plantMass;
    }

    public synchronized int getRow() {
        return row;
    }

    public synchronized int getCol() {
        return col;
    }

    public synchronized double getPlantMass() {
        return plantMass;
    }

    public synchronized void setPlantMass(double plantMass) {
        this.plantMass = plantMass;
    }

    public synchronized void decreasePlantMass(double amount) {
        plantMass = Math.max(0, plantMass - amount);
    }

    public synchronized List<Animal> getAnimals() {
        List<Animal> animals = new ArrayList<>();
        for (List<Organism> orgList : organisms.values()) {
            for (Organism organism : orgList) {
                if (organism instanceof Animal) {
                    animals.add((Animal) organism);
                }
            }
        }
        return animals;
    }

    public synchronized void addOrganism(Organism organism) {
        String type = organism.getClass().getSimpleName();
        organisms.putIfAbsent(type, new ArrayList<>());
        if (hasSpaceFor((Animal) organism)) {
            organisms.get(type).add(organism);
        }
    }

    public synchronized void removeOrganism(Organism organism) {
        String type = organism.getClass().getSimpleName();
        if (organisms.containsKey(type)) {
            organisms.get(type).remove(organism);
            if (organisms.get(type).isEmpty()) {
                organisms.remove(type);
            }
        }
    }

    public synchronized boolean hasSpaceFor(Animal animal) {
        String type = animal.getClass().getSimpleName();
        int currentCount = organisms.getOrDefault(type, Collections.emptyList()).size();
        return currentCount < animal.maxCountPerCell;
    }

    public synchronized List<Organism> getOrganismsByType(String type) {
        return organisms.getOrDefault(type, Collections.emptyList());
    }
}