package ua.com.javarush.gnew.Predator;

import ua.com.javarush.gnew.Animal.Animal;
import ua.com.javarush.gnew.Field.Cell;
import ua.com.javarush.gnew.Field.Field;
import ua.com.javarush.gnew.Plant.Plant;
import ua.com.javarush.gnew.herbivore.*;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Wolf extends Animal {

    public Wolf(String name, double weight, int quantity, int foodNeeded) {
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
            log("Волк переместился из (" + x + ", " + y + ") в (" + newX + ", " + newY + ").");
        }
    }


    @Override
    public void eat(Cell[][] cells, int x, int y) {
        Rabbit prey = findPrey(Rabbit.class, cells, x, y);
        Sheep prey2 = findPrey(Sheep.class,cells, x, y);
        Horse prey3 = findPrey(Horse.class,cells, x, y);
        Deer prey4 = findPrey(Deer.class,cells, x, y);
        Boar prey5 = findPrey(Boar.class,cells, x, y);// Ищем кроликов
        if (prey != null) {
            prey.setAlive(false);
            System.out.println(getName() + " съел кролика!");
            increaseSaturation(prey.getWeight());
        }else if (prey2 != null) {
            prey2.setAlive(false);
            System.out.println(getName() + " съел овцу!");
            increaseSaturation(prey2.getWeight());
        }else if (prey3 != null) {
            prey3.setAlive(false);
            System.out.println(getName() + " съел лошадь!");
            increaseSaturation(prey3.getWeight());
        }else if (prey4 != null) {
            prey4.setAlive(false);
            System.out.println(getName() + " съел оленя!");
            increaseSaturation(prey4.getWeight());
        }else if (prey5 != null) {
            prey5.setAlive(false);
            System.out.println(getName() + " съел кабана!");
            increaseSaturation(prey5.getWeight());
        } else {
            System.out.println(getName() + " не нашёл добычи.");
        }
    }

    @Override
    public void reproduce(Field field) {
        if (this.getSaturation() >= this.getFoodNeeded()) {
            Wolf newWolf = new Wolf("Волк", 50, 1, 8);
            Cell randomCell = field.getRandomEmptyCell();
            if (randomCell != null) {
                randomCell.setAnimal(newWolf);
                log("Волк размножился в клетке (" + randomCell.getX() + ", " + randomCell.getY() + ").");
                this.decreaseSaturation(4);
            }
        }
    }

    @Override
    public void death() {
        if (this.getSaturation() <= 0) {
            this.setAlive(false);
            log("Волк умер от голода.");
        }
    }
}



