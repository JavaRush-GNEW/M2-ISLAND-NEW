package org.example.entity.map;


import org.example.entity.animal.Animal;
import org.example.entity.animal.herbivore.Herbivore;
import org.example.entity.animal.interfaces.EatingPlant;
import org.example.entity.animal.interfaces.Hunting;
import org.example.entity.animal.interfaces.Reproduction;
import org.example.entity.animal.predator.Predator;
import org.example.entity.plant.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class Island {
    private final Cell[][] GRID;

    public Island(int width, int height) {
        GRID = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                GRID[i][j] = new Cell();
            }
        }
        IslandGenerator islandGenerator = new IslandGenerator();
        islandGenerator.populateIsland(GRID,100);

        System.out.println("Початковий стан острова:");
        printIsland(getGRID());

        // Movable //
        moveAllAnimals(getGRID());

        // CONSOLE checked update move
        System.out.println("\nСтан острова після переміщення:");
        printIsland(getGRID());
        System.out.println("_________________________");
        printAnimalStatisticsSimple("До полювання");
        System.out.println("_________________________");
        System.out.println("Початок полювання");
        huntAllAnimals();

        System.out.println("_________________________");
        printAnimalStatisticsSimple("Після полювання");
        System.out.println("_________________________");
        System.out.println("Початок розмноження");
        System.out.println("_________________________");
        handleReproduction();
        System.out.println("_________________________");
        printAnimalStatisticsSimple("Після розмноження");
    }


    public Cell[][] getGRID() {
        return GRID;
    }
            // MOVE OUR ANIMALS //
    public static void moveAllAnimals(Cell[][] island) {
        for (int i = 0; i < island.length; i++) {
            for (int j = 0; j < island[i].length; j++) {
                Cell cell = island[i][j];
                // Copy list //
                List<Animal> animalsToMove = new ArrayList<>(cell.getEntities());

                for (Animal animal : animalsToMove) {
                    // Random steps 0 -> maxStep
                    int steps = new Random().nextInt(animal.getMaxStep() + 1);
                    int[] newCoordinates = getTargetCell(island, i, j, steps);


                    cell.removeEntity(animal);

                    // Add animal to new cell //
                    island[newCoordinates[0]][newCoordinates[1]].addEntity(animal);

//                    System.out.printf("Тварина %s перемістилася з [%d, %d] до [%d, %d]%n",
//                            animal.getClass().getSimpleName(), i, j, newCoordinates[0], newCoordinates[1]);
                }
            }
        }
    }
    private static int[] getTargetCell(Cell[][] island, int startX, int startY, int steps) {
        int width = island[0].length;
        int height = island.length;

        int x = startX;
        int y = startY;


        for (int step = 0; step < steps; step++) {
            List<int[]> neighbors = new ArrayList<>();

            if (x > 0) neighbors.add(new int[]{x - 1, y}); // UP cell
            if (x < height - 1) neighbors.add(new int[]{x + 1, y}); // Down cell
            if (y > 0) neighbors.add(new int[]{x, y - 1}); // Left
            if (y < width - 1) neighbors.add(new int[]{x, y + 1}); // Right

            int[] next = neighbors.get(new Random().nextInt(neighbors.size())); // Random neighbor
            x = next[0];
            y = next[1];
        }

        return new int[]{x, y}; // final coordinate
    }
            // HUNT OUR ANIMALS //
    public void huntAllAnimals() {
        for (Cell[] row : getGRID()) {
            for (Cell cell : row) {
                // Hunter in cell //
                List<Hunting> hunters = cell.getEntities().stream()
                        .filter(entity -> entity instanceof Hunting)
                        .map(entity -> (Hunting) entity)
                        .collect(Collectors.toList());
                List<EatingPlant> eatingPlants = cell.getEntities().stream()
                        .filter(entity -> entity instanceof EatingPlant)
                        .map(entity -> (EatingPlant) entity)
                        .collect(Collectors.toList());

                // All Hunter //
                eatingPlants.forEach(eatingPlant -> {
                    eatingPlant.eatPlant(cell);
                });

                for (Hunting hunter : hunters) {
                    hunter.hunt(cell);

                }
            }
        }
    }


             // CONSOLE TEST WORK OUR PROJECT //
    private static void printIsland(Cell[][] island) {
        for (Cell[] row : island) {
            for (Cell cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
    public void printAnimalStatisticsSimple(String message) {
        int predatorCount = 0;
        int herbivoreCount = 0;
        int plantCount = 0;

        for (Cell[] row : getGRID()) {
            for (Cell cell : row) {
                // Використовуємо stream для підрахунку тварин і рослин
                long predatorsInCell = cell.getEntities().stream().filter(animal -> animal instanceof Predator).count();
                long herbivoresInCell = cell.getEntities().stream().filter(animal -> animal instanceof Herbivore).count();
                long plantsInCell = cell.getPlants().stream().filter(plant -> plant instanceof Plant).count();

                predatorCount += predatorsInCell;
                herbivoreCount += herbivoresInCell;
                plantCount += plantsInCell;
            }
        }

        // Виведення статистики
        System.out.println(message);
        System.out.println("Кількість Predators: " + predatorCount);
        System.out.println("Кількість Herbivores: " + herbivoreCount);
        System.out.println("Кількість Plants: " + plantCount);
    }


    public void handleReproduction() {
        int successfulReproductions = 0;
        int failedReproductions = 0;

        for (Cell[] row : getGRID()) {
            for (Cell cell : row) {
                List<Animal> animals = new ArrayList<>(cell.getEntities()); // Копія списку
                for (Animal animal : animals) {
                    if (animal instanceof Reproduction) {
                        boolean isSaturationEnough = animal.getSaturation() > animal.getMaxSaturation() / 2;

                        if (isSaturationEnough) {
                            // Проводимо розмноження

                            ((Reproduction) animal).reproduce(cell);
                            successfulReproductions++;
                        } else {
                            failedReproductions++;
                            if (!isSaturationEnough) {
//                                           System.out.println(animal.getClass().getSimpleName() + " не зміг розмножитися через низький рівень насичення");
                            }
                        }
                    }
                }
            }
        }

        // Виведення статистики
        System.out.println("Результати розмноження:");
        System.out.println("Успішні розмноження: " + successfulReproductions);
        System.out.println("Невдалі спроби розмноження: " + failedReproductions);
    }



}

