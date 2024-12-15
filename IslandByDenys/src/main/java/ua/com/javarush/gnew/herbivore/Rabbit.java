package ua.com.javarush.gnew.herbivore;

import ua.com.javarush.gnew.Animal.Animal;
import ua.com.javarush.gnew.Field.Cell;
import ua.com.javarush.gnew.Field.Field;
import ua.com.javarush.gnew.Plant.Plant;

import java.util.ArrayList;
import java.util.List;

public class Rabbit extends Animal {

    public Rabbit(String name, double weight, int quantity, double foodNeeded) {
        super(name, weight, quantity, foodNeeded);
    }

    @Override
    public boolean isHerbivore() {
        return true;  // Кролик - травоядное животное
    }

    @Override
    public void move(Cell[][] cells, int x, int y) {
        Cell emptyCell = getRandomEmptyCell(cells, x, y);
        if (emptyCell != null) {
            // Перемещаем кролика в пустую клетку
            emptyCell.setAnimal(this);
            cells[x][y].setAnimal(null);

            System.out.printf("Кролик переместился с (%d, %d) на (%d, %d)\n", x, y, emptyCell.getX(), emptyCell.getY());
        }
    }

    @Override
    public void eat(Cell[][] cells, int x, int y) {
        // Ищем растение
        Plant plant = findPrey(Plant.class, cells, x, y);
        if (plant != null) {
            // Кролик съедает растение
            this.increaseSaturation(plant.getWeight());  // Увеличение насыщения
            plant.setEaten(true);  // Отмечаем растение как съеденное
            System.out.println(getName() + " съел растение!");
            cells[x][y].setPlant(null);  // Убираем растение с клетки
        } else {
            System.out.println(getName() + " не нашёл растений для еды.");
        }
    }

    @Override
    public void reproduce(Field field) {
        if (this.getSaturation() >= this.getFoodNeeded()) {
            Cell randomEmptyCell = field.getRandomEmptyCell();
            if (randomEmptyCell != null) {
                Rabbit newRabbit = new Rabbit("Rabbit", 2, 1, 0.45);
                randomEmptyCell.setAnimal(newRabbit);

                System.out.printf("Кролик размножился в клетке (%d, %d)\n", randomEmptyCell.getX(), randomEmptyCell.getY());
                this.decreaseSaturation(0.20); // Уменьшаем насыщение после размножения
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

    private <T> T findPrey(Class<T> preyClass, Cell[][] cells, int x, int y) {
        for (int i = Math.max(0, x - 1); i <= Math.min(cells.length - 1, x + 1); i++) {
            for (int j = Math.max(0, y - 1); j <= Math.min(cells[0].length - 1, y + 1); j++) {
                if (preyClass == Animal.class) {
                    Animal potentialPrey = cells[i][j].getAnimal();
                    if (potentialPrey != null && potentialPrey.isAlive()) {
                        return preyClass.cast(potentialPrey);
                    }
                } else if (preyClass == Plant.class) {
                    Plant potentialPlant = cells[i][j].getPlant();
                    if (potentialPlant != null && !potentialPlant.isEaten()) {
                        return preyClass.cast(potentialPlant);
                    }
                }
            }
        }
        return null;
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

        return null; // Нет пустых клеток
    }
}



