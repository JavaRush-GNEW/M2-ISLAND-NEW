package Organism;

import GameMap.Cell;
import GameMap.Island;

public abstract class Organism {
    protected String name;
    protected double weight;

    public Organism(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public abstract void act(Cell cell, Island island);
}

