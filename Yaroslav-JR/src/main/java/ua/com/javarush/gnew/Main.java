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


        for (int i = 0; i < 1000; i++){
            for (int j = 0; j < 1000; j++){

                Cell cell = island.getField()[i][j];

                int wolfCount = random.nextInt(21);
                int sheepCount = random.nextInt(61);
                int grassCount = random.nextInt(101);


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

        Arrays.stream(island.getField())
                .flatMap(Arrays::stream)
                .forEach(cell -> {
                    cell.getAnimals().stream()
                            .filter(animal -> animal instanceof Sheep)
                            .map(animal -> (Sheep) animal)
                            .forEach(sheep -> {
                               sheep.eat(cell);
                               sheep.reproduce(cell);
                               sheep.move();
                            });
                });
        Arrays.stream(island.getField())
                .flatMap(Arrays::stream)
                .forEach(cell -> {
                    cell.getGrass().stream()
                            .filter(grass -> grass instanceof Grass)
                            .map(grass -> (Grass) grass)
                            .forEach(grass -> {
                                grass.grow(cell);
                            });
                });

        Arrays.stream(island.getField())
                .flatMap(Arrays::stream)
                .forEach(cell -> {
                    cell.getAnimals().stream()
                            .filter(animal -> animal instanceof Wolf)
                            .map(animal -> (Wolf) animal)
                            .forEach(wolf -> {
                                wolf.eat(cell);
                                wolf.reproduce(cell);
                                wolf.move();
                            });
                });
    }
}