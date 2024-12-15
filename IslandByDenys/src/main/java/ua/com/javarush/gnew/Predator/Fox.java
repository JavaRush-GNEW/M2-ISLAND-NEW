package ua.com.javarush.gnew.Predator;

import ua.com.javarush.gnew.Animal.Animal;
import ua.com.javarush.gnew.Field.Cell;
import ua.com.javarush.gnew.Field.Field;
import ua.com.javarush.gnew.herbivore.*;

public class Fox extends Animal {

    public Fox(String name, double weight, int quantity, int foodNeeded) {
        super(name, weight, quantity, foodNeeded);
    }

    @Override
    public boolean isHerbivore() {
        return false;  // Волк - хищное животное
    }

    private <T extends Animal> T findPrey(Class<T> preyClass, Cell[][] cells, int x, int y) {
        for (int i = Math.max(0, x - 1); i <= Math.min(cells.length - 1, x + 1); i++) {
            for (int j = Math.max(0, y - 1); j <= Math.min(cells[0].length - 1, y + 1); j++) {
                Animal potentialPrey = cells[i][j].getAnimal();
                if (preyClass.isInstance(potentialPrey) && potentialPrey.isAlive()) {
                    return preyClass.cast(potentialPrey);
                }
            }
        }
        return null;
    }


    @Override
    public void move(Cell[][] cells, int x, int y) {
        int newX = x + (int) (Math.random() * 3) - 1;
        int newY = y + (int) (Math.random() * 3) - 1;

        if (newX >= 0 && newX < cells.length && newY >= 0 && newY < cells[0].length) {
            cells[newX][newY].setAnimal(this);
            cells[x][y].setAnimal(null);
            log("Лиса переместилась из (" + x + ", " + y + ") в (" + newX + ", " + newY + ").");
        }
    }


    @Override
    public void eat(Cell[][] cells, int x, int y) {
        Rabbit prey = findPrey(Rabbit.class, cells, x, y);
        if (prey != null) {
            prey.setAlive(false);
            System.out.println(getName() + " съела кролика!");
            increaseSaturation(prey.getWeight());
        } else {
            System.out.println(getName() + " не нашла добычи.");
        }
    }

    @Override
    public void reproduce(Field field) {
        if (this.getSaturation() >= this.getFoodNeeded()) {
            Wolf newWolf = new Wolf("Лиса", 8, 1, 2);
            Cell randomCell = field.getRandomEmptyCell();
            if (randomCell != null) {
                randomCell.setAnimal(newWolf);
                log("Лиса размножилась в клетке (" + randomCell.getX() + ", " + randomCell.getY() + ").");
                this.decreaseSaturation(1);
            }
        }
    }

    @Override
    public void death() {
        if (this.getSaturation() <= 0) {
            this.setAlive(false);
            log("Лиса умерла от голода.");
        }
    }
}

