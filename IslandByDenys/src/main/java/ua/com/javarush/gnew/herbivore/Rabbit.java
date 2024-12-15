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

public class Rabbit extends Animal implements Herbivores, Logging, HerbivoresFindPrey {

    public Rabbit(String name, double weight, int quantity, double foodNeeded) {
        super(name, weight, quantity, foodNeeded);
    }

    @Override
    public void move(Cell[][] cells, int x, int y) {
        Cell emptyCell = getRandomEmptyCell(cells, x, y);
        if (emptyCell != null) {
            emptyCell.setAnimal(this);
            cells[x][y].setAnimal(null);

            System.out.printf("Кролик переместился с (%d, %d) на (%d, %d)\n", x, y, emptyCell.getX(), emptyCell.getY());
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
            System.out.println(getName() + " не нашёл растений для еды.");
        }
    }

    @Override
    public void reproduce(Field field) {
        if (this.getSaturation() >= this.getFoodNeeded()) {
            Cell randomEmptyCell = field.getRandomEmptyCell();
            if (randomEmptyCell != null) {
                Rabbit newRabbit = new Rabbit("Кролик", 2, 1, 0.45);
                randomEmptyCell.setAnimal(newRabbit);

                System.out.printf("Кролик размножился в клетке (%d, %d)\n", randomEmptyCell.getX(), randomEmptyCell.getY());
                this.decreaseSaturation(0.20);
            }
        }
    }

    @Override
    public void death() {
        if (this.getSaturation() <= 0) {
            this.setAlive(false);
            log("Кролик умер от голода");
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



