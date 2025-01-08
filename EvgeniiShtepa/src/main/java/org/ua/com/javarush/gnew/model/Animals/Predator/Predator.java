package org.ua.com.javarush.gnew.model.Animals.Predator;

import org.ua.com.javarush.gnew.Island.Cell;
import org.ua.com.javarush.gnew.model.Animals.Animal;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Predator extends Animal {


    public Predator(int maxCellCount, int maxStepsCount, int weight, int foodKgRequired) {
        super(maxCellCount, maxStepsCount, weight, foodKgRequired);
    }

    @Override
    public void eat(Cell currentCell) {
        if (this.getSatiety() < this.getFOOD_KG_REQUIRED()) {
            Set<Class<? extends Organism>> eatProbabilityAnimals = this.getEatProbability().keySet();
            Set<Class<? extends Organism>> cellAnimalsClasses = currentCell.getResidents().keySet();

            Optional<Class<? extends Organism>> meal = eatProbabilityAnimals.stream()
                    .filter(cellAnimalsClasses::contains)
                    .findAny();

            meal.ifPresent(clazz -> {
                int eatProbability = this.getEatProbability().get(clazz);
                int chance = ThreadLocalRandom.current().nextInt(100);
                if (chance < eatProbability) {
                    Optional<Organism> first = currentCell.getResidents().get(clazz).stream().findFirst();
                    first.ifPresent(prey -> {
                        currentCell.removeAnimal(prey);
                        int prayWeight = prey.getWeight();
                        setSatiety(getSatiety() + prayWeight);
                    });
                }
            });
        }
    }
}
