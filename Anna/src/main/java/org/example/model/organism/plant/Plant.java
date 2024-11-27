package org.example.model.organism.plant;

import org.example.model.map.Cell;
import org.example.model.organism.Organism;
import org.example.model.organism.Reproducible;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Plant implements Organism, Reproducible {
    private static final double WEIGHT = 1;
    private static final int MAX_QUANTITY_AT_CELL = 200;
    private final UUID id = UUID.randomUUID();

    @Override
    public boolean die() {
        return false;
    }

    @Override
    public void lifeCycle() {
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
    public boolean reproduce(Cell cell) {
        Set<Organism> sameSpecies = cell.getResidents().get(this.getClass());
        if (sameSpecies == null || sameSpecies.size() < 2) {
            return false;
        }
        if (sameSpecies.size() >= MAX_QUANTITY_AT_CELL) {
            return false;
        }
        Organism partner = sameSpecies.stream()
                .filter(item -> item instanceof Plant && item.getClass() == this.getClass() && item != this)
                .findFirst()
                .orElse(null);

        if (partner == null) {
            return false;
        }

        Plant offspring = createOffspring();
        return cell.add(offspring);
    }

    private Plant createOffspring() {
        return new Plant();
    }
}
