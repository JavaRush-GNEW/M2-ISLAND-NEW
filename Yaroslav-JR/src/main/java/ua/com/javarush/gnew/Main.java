package ua.com.javarush.gnew;

import ua.com.javarush.gnew.config.Context;
import ua.com.javarush.gnew.config.Loader;
import ua.com.javarush.gnew.entity.Animal;
import ua.com.javarush.gnew.entity.Organism;
import ua.com.javarush.gnew.entity.chewingGrass.Sheep;
import ua.com.javarush.gnew.entity.island.Cell;
import ua.com.javarush.gnew.entity.island.Island;
import ua.com.javarush.gnew.entity.meatEaters.Wolf;
import ua.com.javarush.gnew.entity.plant.Grass;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Loader loader = Loader.getINSTANCE();
        Context context = loader.init();
        Island island = context.getIsland();

        ConcurrentHashMap<Class<? extends Organism>, Integer> animalCounts = countAnimals(island);
        printAnimalCounts(animalCounts, "\nInitial count of animals on the island:\n");

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(() -> processAnimals(island, 0, island.getWidth() / 2));
        executorService.execute(() -> processAnimals(island, island.getWidth() / 2, island.getWidth()));

        ScheduledExecutorService monitorService = Executors.newSingleThreadScheduledExecutor();
        monitorService.scheduleWithFixedDelay(() -> {
            ConcurrentHashMap<Class<? extends Organism>, Integer> currentCounts = countAnimals(island);
            printAnimalCounts(currentCounts, "\nCurrent animal counts:\n");
        }, 0, 2, TimeUnit.SECONDS);

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        executorService.shutdownNow();
        monitorService.shutdownNow();

        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            monitorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static ConcurrentHashMap<Class<? extends Organism>, Integer> countAnimals(Island island) {
        ConcurrentHashMap<Class<? extends Organism>, Integer> animalCounts = new ConcurrentHashMap<>();
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getHeight(); j++) {
                Cell cell = island.getField()[i][j];
                    cell.getResidents().forEach((animalClass, organisms) ->
                            animalCounts.merge(animalClass, organisms.size(), Integer::sum));
            }
        }
        return animalCounts;
    }

    private static void printAnimalCounts(ConcurrentHashMap<Class<? extends Organism>, Integer> animalCounts, String message) {
        System.out.println(message);
        for (ConcurrentHashMap.Entry<Class<? extends Organism>, Integer> entry : animalCounts.entrySet()) {
            System.out.println("Animal: " + entry.getKey().getSimpleName() + ", Count: " + entry.getValue());
        }
    }

    private static void processAnimals(Island island, int startX, int endX) {
        for (int x = startX; x < endX; x++) {
            for (int y = 0; y < island.getHeight(); y++) {
                Cell cell = island.getField()[x][y];
                    int finalX = x;
                    int finalY = y;
                    cell.getResidents().forEach((type, organisms) -> {
                        for (Organism organism : organisms) {
                            if (organism instanceof Animal) {
                                Animal animal = (Animal) organism;
                                animal.eat(cell);
                                animal.reproduce(cell);
                                animal.move(cell, island, finalX, finalY);
                            }
                        }
                    });

            }
        }
    }
}


