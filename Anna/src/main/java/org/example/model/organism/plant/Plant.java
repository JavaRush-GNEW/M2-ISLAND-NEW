package org.example.model.organism.plant;

import org.example.model.map.Cell;
import org.example.model.organism.Organism;
import org.example.model.organism.Reproducible;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Plant implements Organism, Reproducible {
    private static final double PLANT_WEIGHT = 1;
    private static final int MAX_POPULATION = 200;
    private final UUID id = UUID.randomUUID();
    private Cell cell;

    @Override
    public Cell getCell() {
        return cell;
    }

    @Override
    public void setCell(Cell cell) {
        this.cell = cell;
    }

    @Override
    public int getMaxPopulation() {
        return MAX_POPULATION;
    }

    @Override
    public boolean die() {
        if (cell == null) {
            return false;
        }
        return cell.remove(this);
    }

    @Override
    public void lifeCycle() {
        reproduce();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return Objects.equals(id, plant.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean reproduce() {
        Set<Organism> sameSpecies = cell.getResidents().get(this.getClass());
        if (sameSpecies == null || sameSpecies.size() >= MAX_POPULATION) {
            return false;
        }

        Plant offspring = createOffspring();
        return cell.add(offspring);
    }

    private Plant createOffspring() {
        return new Plant();
    }
}