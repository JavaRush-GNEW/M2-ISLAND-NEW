package org.ua.com.javarush.gnew.model.Animals;

import lombok.Getter;
import lombok.Setter;
import org.ua.com.javarush.gnew.Island.Cell;
import org.ua.com.javarush.gnew.Island.IslandMap;
import org.ua.com.javarush.gnew.exeptions.ReproduceException;
import org.ua.com.javarush.gnew.model.Animals.Intarfaces.Organism;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public abstract class Animal implements Organism {
    private final int MAX_CELL_COUNT;
    private final int MAX_STEPS_COUNT;
    private final int WEIGHT;
    private final int FOOD_KG_REQUIRED;
    @Setter
    private int satiety;


    protected Animal(int maxCellCount, int maxStepsCount, int weight, int foodKgRequired) {
        this.MAX_CELL_COUNT = maxCellCount;
        this.MAX_STEPS_COUNT = maxStepsCount;
        this.WEIGHT = weight;
        this.FOOD_KG_REQUIRED = foodKgRequired;
        this.satiety = foodKgRequired / 2;
    }

    public void move(IslandMap island, Cell currentCell) {
        int steps = getMAX_STEPS_COUNT();
        int x = currentCell.getX();
        int y = currentCell.getY();

        int width = island.getWidth();
        int height = island.getHeight();

        for (int i = 0; i < steps; i++) {
            List<Direction> possibleDirections = new ArrayList<>();

            if (y + 1 < height) {
                possibleDirections.add(Direction.UP);
            }
            if (y - 1 >= 0) {
                possibleDirections.add(Direction.DOWN);
            }
            if (x - 1 >= 0) {
                possibleDirections.add(Direction.LEFT);
            }
            if (x + 1 < width) {
                possibleDirections.add(Direction.RIGHT);
            }
            if (possibleDirections.isEmpty()) {
                continue;
            }

            Direction chosen = possibleDirections.get(ThreadLocalRandom.current().nextInt(possibleDirections.size()));
            switch (chosen) {
                case UP -> y++;
                case DOWN -> y--;
                case LEFT -> x--;
                case RIGHT -> x++;
            }
        }

        if (x != currentCell.getX() || y != currentCell.getY()) {
            currentCell.removeAnimal(this);
            island.getCells()[y][x].addAnimal(this);
            satiety--;

        }
    }

    public void reproduce(Cell currentCell) {
        int chance = ThreadLocalRandom.current().nextInt(100);
        int animalCountInCell = currentCell.getResidents().get(this.getClass()).size();
        if (animalCountInCell < this.MAX_CELL_COUNT) {
            if (chance < 30) {
                try {
                    currentCell.addAnimal(this.getClass().getDeclaredConstructor().newInstance());
                } catch (Exception e) {
                    throw new ReproduceException("Не удалось размножить класс " + this.getClass().getSimpleName(), e);
                }

            }
        }
    }

    @Override
    public void isAnimalAlive(Cell currentCell) {
        if (satiety == 0) {
            currentCell.removeAnimal(this);

        }
    }

    @Override
    public int getWeight() {
        return WEIGHT;
    }
}
