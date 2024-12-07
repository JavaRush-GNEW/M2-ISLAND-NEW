package Config;

import GameMap.Cell;
import GameMap.Island;
import Organism.Animal.Animal;
import Organism.Animal.Herbivore.Animals.*;
import Organism.Animal.Predator.Animals.*;
import Organism.Animal.Omnivore.Animals.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class IslandSimulationManager {
    private static final double MAX_PLANT_MASS = 200.0;
    private final Island island;
    private final ExecutorService executor;
    private int dayCounter = 0;

    public IslandSimulationManager(int rows, int cols) {
        this.island = new Island(rows, cols);
        this.executor = Executors.newFixedThreadPool(10);
        initializeIsland(rows, cols);
    }

    private void initializeIsland(int rows, int cols) {
        int initialAnimals = Math.max(1, (rows * cols) / 5);

        for (int i = 0; i < initialAnimals; i++) {
            island.getRandomCell().addOrganism(new Wolf());
            island.getRandomCell().addOrganism(new Rabbit());
            island.getRandomCell().addOrganism(new Bear());
            island.getRandomCell().addOrganism(new Python());
            island.getRandomCell().addOrganism(new Fox());
            island.getRandomCell().addOrganism(new Eagle());
            island.getRandomCell().addOrganism(new Horse());
            island.getRandomCell().addOrganism(new Goat());
            island.getRandomCell().addOrganism(new Sheep());
            island.getRandomCell().addOrganism(new Buffalo());
            island.getRandomCell().addOrganism(new Bear());
            island.getRandomCell().addOrganism(new Duck());
            island.getRandomCell().addOrganism(new WildBoar());
            island.getRandomCell().addOrganism(new Mouse());
        }
    }

    public void startSimulation(int days, int stepsPerDay) {
        for (int day = 1; day <= days; day++) {
            System.out.println("День " + day);
            for (int step = 1; step <= stepsPerDay; step++) {
                simulateDayParallel(dayCounter);
                dayCounter++;

                if (!island.hasLivingAnimals()) {
                    System.out.println("Усі тварини померли. Симуляцію завершено.");
                    executor.shutdown();
                    return;
                }
            }
            island.printAnimalStatistics();
        }
        executor.shutdown();
    }

    private void simulateDayParallel(int dayCounter) {
        List<Future<?>> tasks = new ArrayList<>();

        for (Cell[] row : island.getGrid()) {
            for (Cell cell : row) {
                tasks.add(executor.submit(() -> {
                    List<Animal> animals = new ArrayList<>(cell.getAnimals());
                    for (Animal animal : animals) {
                        animal.act(cell, island);
                    }
                    cell.updateOrganisms();
                }));
            }
        }

        waitForCompletion(tasks);

        resetReproduceFlags();

        if (dayCounter % 5 == 0) {
            growPlantsParallel();
        }
    }

    private void growPlantsParallel() {
        List<Future<?>> tasks = new ArrayList<>();

        for (Cell[] row : island.getGrid()) {
            for (Cell cell : row) {
                tasks.add(executor.submit(() -> {
                    cell.setPlantMass(Math.min(MAX_PLANT_MASS, cell.getPlantMass() + 3));
                }));
            }
        }

        waitForCompletion(tasks);
    }

    private void waitForCompletion(List<Future<?>> tasks) {
        for (Future<?> task : tasks) {
            try {
                task.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
    private void resetReproduceFlags() {
        List<Future<?>> tasks = new ArrayList<>();

        for (Cell[] row : island.getGrid()) {
            for (Cell cell : row) {
                tasks.add(executor.submit(() -> {
                    synchronized (cell) {
                        List<Animal> animals = new ArrayList<>(cell.getAnimals());
                        for (Animal animal : animals) {
                            animal.resetReproduceFlag();
                        }
                    }
                }));
            }
        }

        waitForCompletion(tasks);
    }
}
