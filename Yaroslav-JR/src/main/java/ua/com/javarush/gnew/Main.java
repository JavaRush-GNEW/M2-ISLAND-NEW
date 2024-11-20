package ua.com.javarush.gnew;

import ua.com.javarush.gnew.entity.chewingGrass.Sheep;
import ua.com.javarush.gnew.entity.island.Cell;
import ua.com.javarush.gnew.entity.island.Island;
import ua.com.javarush.gnew.entity.meatEaters.Wolf;
import ua.com.javarush.gnew.entity.plant.Grass;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        Island island = new Island();

        int totalWolfCount = 0;
        int totalSheepCount = 0;
        int totalGrassCount = 0;
        for (int i = 0; i < 1000; i++){
            for (int j = 0; j < 1000; j++){

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
        System.out.println(totalWolfCount + "wolves appeared on the island");
        System.out.println(totalSheepCount + "sheep appeared on the island");
        System.out.println(totalGrassCount + "bushes of grass appeared on the island");

        while (true) {
            for (int x = 0; x < island.getField().length; x++) {
                for (int y = 0; y < island.getField()[x].length; y++) {
                    Cell cell = island.getField()[x][y];

                    cell.getAnimals().removeIf(animal -> !animal.isAlive());

                    int finalX = x;
                    int finalY = y;
                    cell.getAnimals().stream()
                            .filter(animal -> animal instanceof Sheep)
                            .map(animal -> (Sheep) animal)
                            .forEach(sheep -> {
                                sheep.eat(cell);
                                sheep.reproduce(cell);
                                sheep.move(cell, island, finalX, finalY);
                            });
                }
            }
            for (int x = 0; x < island.getField().length; x++) {
                for (int y = 0; y < island.getField()[x].length; y++) {
                    Cell cell = island.getField()[x][y];

                    cell.getGrass().removeIf(grass -> !grass.isAlive());

                    cell.getGrass().forEach(grass -> grass.grow(cell));
                }
            }

            for (int x = 0; x < island.getField().length; x++) {
                for (int y = 0; y < island.getField()[x].length; y++) {
                    Cell cell = island.getField()[x][y];

                    cell.getAnimals().removeIf(animal -> !animal.isAlive());

                    int finalX = x;
                    int finalY = y;
                    cell.getAnimals().stream()
                            .filter(animal -> animal instanceof Wolf)
                            .map(animal -> (Wolf) animal)
                            .forEach(wolf -> {
                                wolf.eat(cell);
                                wolf.reproduce(cell);
                                wolf.move(cell, island, finalX, finalY);
                            });
                }
            }
            Thread.sleep(3000);
        }
    }
}