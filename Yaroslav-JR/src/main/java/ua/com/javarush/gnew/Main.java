package ua.com.javarush.gnew;

import ua.com.javarush.gnew.entity.Animal;
import ua.com.javarush.gnew.entity.chewingGrass.Sheep;
import ua.com.javarush.gnew.entity.island.Cell;
import ua.com.javarush.gnew.entity.island.Island;
import ua.com.javarush.gnew.entity.meatEaters.Wolf;
import ua.com.javarush.gnew.entity.plant.Grass;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Island island = new Island();
        Random random = new Random();

        int totalWolfCount = 0;
        int totalSheepCount = 0;
        int totalGrassCount = 0;
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){

                Cell cell = island.getField()[i][j];

                int wolfCount = random.nextInt(21);
                totalWolfCount += wolfCount;

                int sheepCount = random.nextInt(61);
                totalSheepCount += sheepCount;

                int grassCount = random.nextInt(101);
                totalGrassCount += grassCount;


                for (int s = 0; s < wolfCount; s++){
                    cell.addAnimal(new Wolf());
                }
                for (int s = 0; s < sheepCount; s++){
                    cell.addAnimal(new Sheep());
                }
                for (int s = 0; s < grassCount; s++){
                    cell.addGrass(new Grass());
                }
            }
        }
        System.out.println(totalWolfCount + " Wolves appeared on the island");
        System.out.println(totalSheepCount + " Sheep appeared on the island");
        System.out.println(totalGrassCount + " Bushes of grass appeared on the island");

        int iterations = 0;

            while (iterations < 5) {
                for (int x = 0; x < island.getField().length; x++) {
                    for (int y = 0; y < island.getField()[x].length; y++) {
                        Cell cell = island.getField()[x][y];

                        Iterator<Animal> iterator = cell.getAnimals().iterator();
                        while (iterator.hasNext()) {
                            Animal animal = iterator.next();
                            if (!animal.isAlive()) {
                                iterator.remove();
                            }
                        }

                        List<Sheep> sheepList = cell.getAnimals().stream()
                                .filter(animal -> animal instanceof Sheep)
                                .map(animal -> (Sheep) animal)
                                .collect(Collectors.toList());

                        for (Sheep sheep : sheepList) {
                            sheep.eat(cell);
                            sheep.reproduce(cell);
                            sheep.move(cell, island, x, y);
                        }
                    }
                }
                for (int x = 0; x < island.getField().length; x++) {
                    for (int y = 0; y < island.getField()[x].length; y++) {
                        Cell cell = island.getField()[x][y];

                        Iterator<Grass> grassIterator = cell.getGrass().iterator();
                        while (grassIterator.hasNext()) {
                            Grass grass = grassIterator.next();
                            if (!grass.isAlive()) {
                                grassIterator.remove();
                            }
                        }

                        for (Grass grass : cell.getGrass()) {
                            grass.grow(cell);
                        }
                    }
                }

                for (int x = 0; x < island.getField().length; x++) {
                    for (int y = 0; y < island.getField()[x].length; y++) {
                        Cell cell = island.getField()[x][y];

                        List<Wolf> wolfList = cell.getAnimals().stream()
                                .filter(animal -> animal instanceof Wolf)
                                .map(animal -> (Wolf) animal)
                                .collect(Collectors.toList());

                        for (Wolf wolf : wolfList) {
                            wolf.eat(cell);
                            wolf.reproduce(cell);
                            wolf.move(cell, island, x, y);
                        }
                    }
                }
                long totalWolves = Arrays.stream(island.getField())
                        .flatMap(Arrays::stream)
                        .flatMap(cell -> cell.getAnimals().stream())
                        .filter(animal -> animal instanceof Wolf)
                        .count();

                long totalSheep = Arrays.stream(island.getField())
                        .flatMap(Arrays::stream)
                        .flatMap(cell -> cell.getAnimals().stream())
                        .filter(animal -> animal instanceof Sheep)
                        .count();

                long totalGrass = Arrays.stream(island.getField())
                        .flatMap(Arrays::stream)
                        .mapToLong(cell -> cell.getGrass().size())
                        .sum();

                System.out.println("Current state of the island:");
                System.out.println("Wolves: " + totalWolves);
                System.out.println("Sheep: " + totalSheep);
                System.out.println("Grass: " + totalGrass);

                iterations++;

                Thread.sleep(3000);
            }
    }
}