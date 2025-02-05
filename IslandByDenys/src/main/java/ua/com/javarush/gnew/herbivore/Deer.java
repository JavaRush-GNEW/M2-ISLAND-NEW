package ua.com.javarush.gnew.herbivore;

import ua.com.javarush.gnew.Animal.Animal;
import ua.com.javarush.gnew.Diet.Herbivores;
import ua.com.javarush.gnew.Field.Cell;
import ua.com.javarush.gnew.Field.Field;
import ua.com.javarush.gnew.FindPrey.HerbivoresFindPrey;
import ua.com.javarush.gnew.Logging.Logging;
import ua.com.javarush.gnew.Plant.Plant;

import java.util.ArrayList;
import java.util.List;

public class Deer extends Animal implements Herbivores, Logging , HerbivoresFindPrey {
    public Deer(String name, double weight, int quantity, int foodNeeded) {
        super(name, weight, quantity, foodNeeded);
    }

    @Override
    public void move(Cell[][] cells, int x, int y) {
        Cell emptyCell = getRandomEmptyCell(cells, x, y);
        if (emptyCell != null) {
            emptyCell.setAnimal(this);
            cells[x][y].setAnimal(null);

            System.out.printf("Олень переместился с (%d, %d) на (%d, %d)\n", x, y, emptyCell.getX(), emptyCell.getY());
        }
    }

    @Override
    public void eat(Cell[][] cells, int x, int y) {
        Plant plant = findPrey(Plant.class, cells, x, y);
        if (plant != null) {
            this.increaseSaturation(plant.getWeight());
            plant.setEaten(true);
            System.out.println(getName() + " съел растение!");
            cells[x][y].setPlant(null);
        } else {
            System.out.println(getName() + " не нашел растений для еды.");
        }
    }

    @Override
    public void reproduce(Field field) {
        if (this.getSaturation() >= this.getFoodNeeded()) {
            Cell randomEmptyCell = field.getRandomEmptyCell();
            if (randomEmptyCell != null) {
                ua.com.javarush.gnew.herbivore.Deer newDeer = new ua.com.javarush.gnew.herbivore.Deer("Олень", 300, 1, 50);
                randomEmptyCell.setAnimal(newDeer);

                System.out.printf("Олень размножился в клетке (%d, %d)\n", randomEmptyCell.getX(), randomEmptyCell.getY());
                this.decreaseSaturation(25);
            }
        }
    }

    @Override
    public void death() {
        if (this.getSaturation() <= 0) {
            this.setAlive(false);
            log("Олень умер от голода");
        }
    }


    private Cell getRandomEmptyCell(Cell[][] cells, int x, int y) {
        List<Cell> emptyCells = new ArrayList<>();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int newX = x + dx;
                int newY = y + dy;

                if (newX >= 0 && newX < cells.length && newY >= 0 && newY < cells[0].length) {
                    if (cells[newX][newY].getAnimal() == null) {
                        emptyCells.add(cells[newX][newY]);
                    }
                }
            }
        }

        if (!emptyCells.isEmpty()) {
            int randomIndex = (int) (Math.random() * emptyCells.size());
            return emptyCells.get(randomIndex);
        }

        return null;
    }
}

