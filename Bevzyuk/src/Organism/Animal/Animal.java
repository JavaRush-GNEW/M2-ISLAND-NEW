
package Organism.Animal;

import GameMap.Cell;
import GameMap.Island;
import Organism.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Organism {
    protected double foodNeed;
    protected double currentFood = 0;
    protected double health = 100;
    protected int speed;
    public int maxCountPerCell;
    protected boolean hasReproduced = false;

    public Animal(String name, double weight, double foodNeed, int speed, int maxCountPerCell) {
        super(name, weight);
        this.foodNeed = foodNeed;
        this.speed = speed;
        this.maxCountPerCell = maxCountPerCell;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void die(Cell cell) {
        if (health <= 0) {
            cell.removeOrganism(this);
        }
    }

    public abstract void eat(Cell cell);

    public void move(Cell from, Island island) {
        List<Cell> availableCells = island.getNeighboringCells(from, speed);

        if (availableCells.isEmpty()) {
            return;
        }
        while (!availableCells.isEmpty()) {
            int randomIndex = ThreadLocalRandom.current().nextInt(availableCells.size());
            Cell target = availableCells.get(randomIndex);

            if (target.hasSpaceFor(this)) {
                from.removeOrganism(this);
                target.addOrganism(this);

                currentFood = Math.max(currentFood - 0.2 * foodNeed, 0);
                return;
            }
            availableCells.remove(randomIndex);
        }
    }

    public void reproduce(Cell cell) {
        if (hasReproduced || currentFood < 0.5 * foodNeed) {
            return;
        }

        List<Organism> sameSpecies = new ArrayList<>(cell.getOrganismsByType(this.getClass().getSimpleName()));
        int availablePairs = (int) sameSpecies.stream()
                .filter(o -> o instanceof Animal && !((Animal) o).hasReproduced)
                .count();

        if (availablePairs > 1) {
            try {
                Animal offspring = this.getClass().getDeclaredConstructor().newInstance();
                cell.addTemporaryOrganism(offspring);
                this.hasReproduced = true;

                Animal pair = (Animal) sameSpecies.stream()
                        .filter(o -> o instanceof Animal && !((Animal) o).hasReproduced)
                        .findFirst()
                        .orElse(null);
                if (pair != null) {
                    pair.hasReproduced = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void resetReproduceFlag() {
        hasReproduced = false;
    }
    public void act(Cell cell, Island island) {
        if (!isAlive()) {
            die(cell);
            return;
        }
        eat(cell);
        if (health > 0) {
            reproduce(cell);
            move(cell, island);
        }
    }
    protected void decreaseHealth(Cell cell) {
        if (currentFood <= 0) {
            health = Math.max(health - 10, 0);
        } else {
            currentFood = Math.max(currentFood - 0.5 * foodNeed, 0);
        }

        if (health <= 0) {
            cell.removeOrganism(this);
        }
    }
}
