package org.example.model.organism.animal;

import org.example.model.map.Cell;
import org.example.model.organism.Movable;
import org.example.model.organism.Organism;
import org.example.model.organism.Reproducible;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;


public abstract class Animal<T extends Animal<T>> implements Organism, Movable, Reproducible {
    private static final double LOST_HEALTH_PER_MOVE = 0.15;
    private static final double LOST_HEALTH_PER_REPRODUCE = 0.2;

    private final UUID id = UUID.randomUUID();
    protected boolean isAlive = true;
    protected double health = 1.0;
    protected double weight;
    protected double foodNeeded;
    protected int speed;
    protected int maxPopulation;

    public Animal() {
    }

    public Animal(double weight, double foodNeeded, int speed, int maxPopulation) {
        this.weight = weight;
        this.foodNeeded = foodNeeded;
        this.speed = speed;
        this.maxPopulation = maxPopulation;
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

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
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
        System.out.println("move");
        if (!isAlive) {
            return;
        }
        health -= LOST_HEALTH_PER_MOVE * health;
        if (health <= 0) {
            die();
        }
    }

    public boolean eat(double foodAmount) {
        System.out.println("eat");
        return true;
    }

    public boolean reproduce(Cell cell) {
        if (!isAlive) {
            return false;
        }
        Set<Organism> sameSpecies = cell.getResidents().get(this.getClass());
        if (sameSpecies == null || sameSpecies.size() < 2) {
            return false;
        }
        if (sameSpecies.size() >= maxPopulation) {
            return false;
        }
        Organism partner = sameSpecies.stream()
                .filter(item -> item instanceof Animal && item.getClass() == this.getClass() && item != this)
                .findFirst()
                .orElse(null);

        if (partner == null) {
            return false;
        }
        this.health -= LOST_HEALTH_PER_REPRODUCE * this.health;
        Animal<T> secondParent = (Animal<T>) partner;

        secondParent.health -= LOST_HEALTH_PER_REPRODUCE * secondParent.health;

        if (!secondParent.isAlive) {
            return false;
        }
        T offspring = createOffspring();
        return cell.add(offspring);
    }

    protected abstract T createOffspring();

    @Override
    public boolean die() {
        return this.getHealth() > 0;
    }

    @Override
    public void lifeCycle() {
        System.out.println("lifeCycle");
    }
}
