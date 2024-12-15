package ua.com.javarush.gnew.MainGame;


import ua.com.javarush.gnew.Animal.Animal;
import ua.com.javarush.gnew.Field.Cell;
import ua.com.javarush.gnew.Field.Field;
import ua.com.javarush.gnew.Plant.Plant;
import ua.com.javarush.gnew.Predator.Bear;
import ua.com.javarush.gnew.Predator.Fox;
import ua.com.javarush.gnew.Predator.Wolf;
import ua.com.javarush.gnew.herbivore.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Field field = new Field(50, 10);

        Random random = new Random();

        for (int i = 0; i < 30; i++) {
            Wolf wolf = new Wolf("Волк", 50, 1, 8);
            placeAnimalRandomly(field, wolf, random);
        }
        for (int i = 0; i < 30; i++) {
            Fox fox = new Fox("Лисица", 8, 1, 2);
            placeAnimalRandomly(field, fox, random);
        }
        for (int i = 0; i < 5; i++) {
            Bear bear = new Bear("Медведь", 70, 1, 15);
            placeAnimalRandomly(field, bear, random);
        }

        for (int i = 0; i < 150; i++) {
            Rabbit rabbit = new Rabbit("Кролик", 2, 1, 1);
            placeAnimalRandomly(field, rabbit, random);
        }

        for (int i = 0; i < 140; i++) {
            Sheep sheep = new Sheep("Sheep", 70, 1, 15);
            placeAnimalRandomly(field, sheep, random);
        }
        for (int i = 0; i < 20; i++) {
            Horse horse = new Horse("Лошадь", 70, 1, 15);
            placeAnimalRandomly(field, horse, random);
        }
        for (int i = 0; i < 20; i++) {
            Deer deer = new Deer("Олень", 70, 1, 15);
            placeAnimalRandomly(field, deer, random);
        }
        for (int i = 0; i < 50; i++) {
            Boar boar = new Boar("Кабан", 70, 1, 15);
            placeAnimalRandomly(field, boar, random);
        }

        for (int i = 0; i < 200; i++) {
            Plant plant = new Plant("Plant", 1, 1);
            placePlantRandomly(field, plant, random);
        }

        Simulation simulation = new Simulation(field);
        simulation.start();

        System.out.println("Симуляция началась. На поле 30 волков, 30 лисиц, 5 медведей,  150 кроликов, 140 овец, 20 лошадей, 20 оленей, 50 кабанов и 200 растений.");
    }

    private static void placeAnimalRandomly(Field field, Animal animal, Random random) {
        int rows = field.getWidth();
        int cols = field.getHeight();
        while (true) {
            int x = random.nextInt(rows);
            int y = random.nextInt(cols);
            Cell cell = field.getCell(x, y);
            if (cell.getAnimal() == null) {
                cell.setAnimal(animal);
                break;
            }
        }
    }


    private static void placePlantRandomly(Field field, Plant plant, Random random) {
        int rows = field.getWidth();
        int cols = field.getHeight();
        while (true) {
            int x = random.nextInt(rows);
            int y = random.nextInt(cols);
            Cell cell = field.getCell(x, y);
            if (cell.getPlant() == null) {
                cell.setPlant(plant);
                break;
            }
        }
    }
}



