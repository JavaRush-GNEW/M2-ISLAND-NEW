
package Organism.Animal;

import GameMap.Cell;
import GameMap.Island;
import Organism.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Organism {
    protected double foodNeed;
    protected double currentFood = 0;
    protected double health = 100;
    protected int speed;
    public int maxCountPerCell;

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
        if (currentFood >= 0.5 * foodNeed) {
            List<Organism> sameSpecies = cell.getOrganismsByType(this.getClass().getSimpleName());
            if (sameSpecies.size() > 1 && sameSpecies.size() < maxCountPerCell) {
                try {
                    Animal offspring = this.getClass().getDeclaredConstructor().newInstance();
                    cell.addOrganism(offspring);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
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
