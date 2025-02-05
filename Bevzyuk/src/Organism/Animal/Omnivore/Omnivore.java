package Organism.Animal.Omnivore;

import GameMap.Cell;
import Organism.Animal.Animal;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Omnivore extends Animal {
    protected Map<String, Double> preyChances;

    public Omnivore(String name, double weight, double foodNeed, int speed, int maxCountPerCell) {
        super(name, weight, foodNeed, speed, maxCountPerCell);
    }

    @Override
    public void eat(Cell cell) {
        boolean successfulHunt = false;

        double availablePlants = cell.getPlantMass();
        double toEat = Math.min(foodNeed - currentFood, availablePlants);
        if (toEat > 0) {
            successfulHunt = true;
            currentFood += toEat;
            cell.decreasePlantMass(toEat);
            if (currentFood >= foodNeed) {
                health = Math.min(health + 10, 100);
                return;
            }
        }

        while (currentFood < foodNeed) {
            List<Animal> potentialPrey = cell.getAnimals();
            boolean preyEaten = false;

            for (Animal prey : potentialPrey) {
                if (preyChances.containsKey(prey.getClass().getSimpleName())) {
                    double chance = preyChances.get(prey.getClass().getSimpleName());
                    if (ThreadLocalRandom.current().nextDouble(100) < chance) {
                        cell.removeOrganism(prey);
                        currentFood = Math.min(currentFood + prey.getWeight(), foodNeed);
                        preyEaten = true;
                        successfulHunt = true;
                        break;
                    }
                }
            }
            if (!preyEaten) {
                break;
            }
        }
        if (currentFood == foodNeed) {
            health = Math.min(health + 15, 100);
        }
        if (!successfulHunt) {
            decreaseHealth(cell);
        }
    }
}
