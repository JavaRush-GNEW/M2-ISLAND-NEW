package Organism.Animal.Herbivore;

import GameMap.Cell;
import Organism.Animal.Animal;

public abstract class Herbivore extends Animal {
    public Herbivore(String name, double weight, double foodNeed, int speed, int maxCountPerCell) {
        super(name, weight, foodNeed, speed, maxCountPerCell);
    }

    @Override
    public void eat(Cell cell) {
        double availablePlants = cell.getPlantMass();
        double toEat = Math.min(foodNeed, availablePlants);
        if (toEat > 0) {
            currentFood += toEat;
            cell.decreasePlantMass(toEat);
            if (currentFood == foodNeed) {
                health = Math.min(health + 10, 100);
            }
        } else {
            decreaseHealth(cell);
        }
    }
}
