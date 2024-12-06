package org.example.model.organism.animal;

import org.example.config.ApplicationContext;
import org.example.model.map.Cell;
import org.example.model.organism.Feeding;
import org.example.model.organism.Movable;
import org.example.model.organism.Organism;
import org.example.model.organism.Reproducible;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public abstract class Animal<T extends Animal<T>> implements Organism, Movable, Reproducible, Feeding {
    private static final double LOST_HEALTH_PER_MOVE = 0.15;
    private static final double LOST_HEALTH_PER_REPRODUCE = 0.2;

    private Cell cell;
    private final UUID id = UUID.randomUUID();
    protected boolean isAlive = true;
    protected double health = 100.0;
    protected double weight;
    protected double foodNeeded;
    protected int speed;
    protected int maxPopulation;
    protected final Map<Class<? extends Organism>, Integer> eatingProbabilities = new HashMap<>();

    protected Animal() {
    }

    protected Animal(double weight, double foodNeeded, int speed, int maxPopulation) {
        this.weight = weight;
        this.foodNeeded = foodNeeded;
        this.speed = speed;
        this.maxPopulation = maxPopulation;
    }

    @Override
    public Cell getCell() {
        return cell;
    }

    @Override
    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public double getFoodNeeded() {
        return foodNeeded;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMaxPopulation() {
        return maxPopulation;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Map<Class<? extends Organism>, Integer> getEatingProbabilities() {
        return eatingProbabilities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return isAlive == animal.isAlive && Double.compare(health, animal.health) == 0 && Double.compare(weight, animal.weight) == 0 && Double.compare(foodNeeded, animal.foodNeeded) == 0 && speed == animal.speed && maxPopulation == animal.maxPopulation && Objects.equals(id, animal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isAlive, health, weight, foodNeeded, speed, maxPopulation);
    }

    public void move() {
        if (!isAlive || cell == null) {
            return;
        }

        List<Cell> possibleCells = calculatePossibleCells();
        if (possibleCells.isEmpty()) {
            return;
        }
        Cell targetCell = selectTargetCell(possibleCells);
        if (targetCell != cell) {
            moveToCell(targetCell);
            decreaseHealthForMove();
        }
        if (health <= 0) {
            die();
        }
    }

    public boolean reproduce() {
        if (!isAlive) {
            return false;
        }
        System.out.println("maxPopulation " + maxPopulation);
        Set<Organism> sameSpecies = cell.getResidents().get(this.getClass());
        if (sameSpecies == null || sameSpecies.size() >= maxPopulation) {
            return false;
        }

        this.health -= LOST_HEALTH_PER_REPRODUCE * this.health;

        T offspring = createOffspring();
        return cell.add(offspring);
    }

    @Override
    public boolean eat() {
        if (!isAlive) {
            return false;
        }

        Map<Class<? extends Organism>, Integer> victims = this.eatingProbabilities;
        Integer probabilityBeingEaten = null;
        for (Map.Entry<Class<? extends Organism>, Integer> victim : victims.entrySet()) {
            probabilityBeingEaten = victim.getValue();
        }

        Map<Class<? extends Organism>, Set<Organism>> residents = cell.getResidents();
        Set<Organism> preyList = new HashSet<>();

        for (Map.Entry<Class<? extends Organism>, Set<Organism>> entry : residents.entrySet()) {
            Class<? extends Organism> organismClass = entry.getKey();

            if (victims.containsKey(organismClass)) {
                preyList.addAll(entry.getValue());
                if (preyList.isEmpty()) {
                    return false;
                }
                Organism possibleFood = preyList.stream().findAny().get();
                String simpleName = possibleFood.getClass().getSimpleName();
                Double foodWeight = null;
                try {
                    Field field = possibleFood.getClass().getDeclaredField(simpleName.toUpperCase() + "_WEIGHT");
                    field.setAccessible(true);
                    foodWeight = (Double) field.get(null);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                int random = ThreadLocalRandom.current().nextInt(0, 100);
                double needed = getFoodNeeded();
                if (random <= probabilityBeingEaten) {
                    if (this.foodNeeded <= foodWeight) {
                        setHealth(100);
                    } else {
                        if (this.health < 100) {
                            health += foodWeight;
                        }
                    }
                    possibleFood.die();
                }
            }
        }
        return getHealth() > 0;
    }

    protected abstract T createOffspring();

    @Override
    public boolean die() {
        if (!isAlive) {
            return false;
        }
        isAlive = false;
        if (cell != null) {
            cell.remove(this);
        }
        return true;
    }

    @Override
    public void lifeCycle() {
        eat();
        if (health <= 0) {
            die();
        }
        reproduce();
        if (health <= 0) {
            die();
        }
        move();
        if (health <= 0) {
            die();
        }
    }

    private void decreaseHealthForMove() {
        health -= LOST_HEALTH_PER_MOVE * health;
        if (health <= 0) {
            die();
        }
    }

    private List<Cell> calculatePossibleCells() {
        List<Cell> possibleCells = new ArrayList<>();
        Cell[][] field = ApplicationContext.getInstance().getGameField().getCells();
        int currentX = cell.getX();
        int currentY = cell.getY();
        int moveDistance = ThreadLocalRandom.current().nextInt(0, speed + 1);

        addCellIfValid(possibleCells, field, currentX - moveDistance, currentY);
        addCellIfValid(possibleCells, field, currentX + moveDistance, currentY);
        addCellIfValid(possibleCells, field, currentX, currentY - moveDistance);
        addCellIfValid(possibleCells, field, currentX, currentY + moveDistance);
        return possibleCells;
    }

    private void addCellIfValid(List<Cell> possibleCells, Cell[][] field, int x, int y) {
        if (x >= 0 && x < field.length && y >= 0 && y < field[0].length) {
            Cell targetCell = field[x][y];
            if (targetCell.canAccommodate(this)) {
                possibleCells.add(targetCell);
            }
        }
    }

    private Cell selectTargetCell(List<Cell> possibleCells) {
        return possibleCells.get(ThreadLocalRandom.current().nextInt(possibleCells.size()));
    }

    private void moveToCell(Cell targetCell) {
        cell.remove(this);
        targetCell.add(this);
        setCell(targetCell);
    }
}
